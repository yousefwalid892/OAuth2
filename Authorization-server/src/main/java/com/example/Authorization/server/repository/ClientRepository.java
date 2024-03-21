package com.example.Authorization.server.repository;

import com.example.Authorization.server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
   Optional<Client> findByClientId(String clientId);
}

