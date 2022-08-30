package com.example.cinemacity.domain.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, String> {
    Optional<Clients> findClientsByEmail(String email);

    Optional<Clients> findById(Long id);
}
