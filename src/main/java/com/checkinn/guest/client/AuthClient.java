package com.checkinn.guest.client;

import com.checkinn.guest.dto.UserAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    private final RestTemplate restTemplate;
    private final String authUrl;

    public AuthClient(@Value("${auth.service.url}") String authUrl) {
        this.restTemplate = new RestTemplate();
        this.authUrl = authUrl;
    }

    public UserAuthResponse validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserAuthResponse> response = restTemplate.exchange(
                authUrl + "/api/auth/validate",
                HttpMethod.GET,
                entity,
                UserAuthResponse.class
        );

        return response.getBody();
    }
}
