/*
package com.example.Authorization.server.controller;

import com.example.Authorization.server.entities.Client;
//import com.example.Authorization.server.service.ClientService;
import com.example.Authorization.server.service.CustomClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private final CustomClientService customClientService;
   // private final ClientService clientService;
    @PostMapping("/create")
    public Client createClient(@RequestBody Client client){
        return customClientService.createClient(client);
    }

}

 */