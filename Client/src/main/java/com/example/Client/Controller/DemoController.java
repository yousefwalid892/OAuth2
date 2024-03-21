package com.example.Client.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    @GetMapping("/token")
    public String token(){
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("3")
                .principal("client")
                .build();
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(request); //request to auth server
        return client.getAccessToken().getTokenValue();
    }
}
