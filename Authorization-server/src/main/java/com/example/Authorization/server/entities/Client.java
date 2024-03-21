package com.example.Authorization.server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String redirectUri;
    private String grantType;
    private String authMethod;

    /*
    public static Client toClient(RegisteredClient registeredClient){
        Client client = new Client();
        client.setClientId(registeredClient.getId());
        client.setClientSecret(registeredClient.getClientSecret());
        client.setScope(registeredClient.getScopes().stream().findAny().get());
        client.setAuthMethod(registeredClient.getClientAuthenticationMethods().stream().findAny().get().getValue());
        client.setGrantType(registeredClient.getAuthorizationGrantTypes().stream().findAny().get().getValue());
        return client;
    }
    public static RegisteredClient toRegistered(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .scope(client.getScope())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .build();
    }

     */
}
