package com.example.ecommercebackend.Security;

public class AuthResponse {
    private String username;
    private String accessToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponse() { }

    public AuthResponse(String email, String accessToken) {
        this.username = email;
        this.accessToken = accessToken;
    }

    // getters and setters are not shown...
}