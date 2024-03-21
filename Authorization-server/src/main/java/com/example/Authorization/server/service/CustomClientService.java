/*
package com.example.Authorization.server.service;

import com.example.Authorization.server.entities.Client;
//import com.example.Authorization.server.mapper.ClientMapper;
import com.example.Authorization.server.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    @Override
    public void save(RegisteredClient registeredClient){
            clientRepository.save(Client.toClient(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findById(Integer.valueOf(id)).orElseThrow(); //convert integer to string
        return Client.toRegistered(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId).orElseThrow();
        return Client.toRegistered(client);
    }
}

 */