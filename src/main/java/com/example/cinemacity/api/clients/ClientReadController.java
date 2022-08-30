package com.example.cinemacity.api.clients;

import com.example.cinemacity.domain.clients.Clients;
import com.example.cinemacity.domain.clients.ClientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientReadController {

    final ClientsRepository clientsRepository;

    @GetMapping("/all")
    public List<Clients> get() {
        return clientsRepository.findAll();
    }
}
