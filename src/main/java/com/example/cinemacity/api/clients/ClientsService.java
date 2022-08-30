package com.example.cinemacity.api.clients;

import com.example.cinemacity.domain.clients.Clients;
import com.example.cinemacity.domain.clients.ClientsRepository;
import com.example.cinemacity.domain.clients.dto.ClientDto;
import com.example.cinemacity.exceptions.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientsService {

    final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ResponseEntity<String> add(ClientDto dto) {
        if(clientsRepository.findClientsByEmail(dto.getEmail()).isPresent()){
            throw new BadRequestException("Exista deja un client cu aceasta adresa de email!");
        }

        Clients clients = new Clients();
        BeanUtils.copyProperties(dto, clients);
        clients.setCreatedAt(LocalDateTime.now());
        clientsRepository.save(clients);

        return ResponseEntity.ok("Clientul a fost adaugat cu succes!");
    }

    public ResponseEntity<String> delete(String id) {
        Clients clients = clientsRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Clientul nu a fost identificat!"));

        clientsRepository.delete(clients);

        return ResponseEntity.ok("Clientul a fost sters cu succes!");
    }

}
