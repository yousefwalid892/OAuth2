package com.example.Authorization.server.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    @Bean
    public SecurityFilterChain AuthServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());       //enable open id connect.

        http.exceptionHandling(   //when someone tries to call an Unauthenticated endpoint then should be redirected to login page.
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login")
                )
        );
        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .redirectUri("http://localhost:8080/login")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)   //used when authenticating the client with the authorization server.
                .authorizationGrantTypes(grant -> {
                    grant.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    grant.add(AuthorizationGrantType.REFRESH_TOKEN);
                //    grant.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                } )
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder()
                .authorizationEndpoint("/oauth2/authorize")
                .tokenEndpoint("/oauth2/token")
                .build();
    }
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        KeyPair kp = kg.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();     //Casting(convert to the RSAPublicKey)
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())    //generate random Key id.
                .build();

        JWKSet set = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(set);
    }
  /*
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer(){   //To add claims
        return context ->
                context.getClaims().claim("test","test");   //will be added in payload(claims)

   */
    }
