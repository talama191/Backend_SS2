package com.example.ecommercebackend.Security;

public class AuthResponse {
    private String username;
    private String accessToken;
    private int userRole;

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }


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

    public AuthResponse() {
    }

    public AuthResponse(String email, String accessToken, int userRole) {
        this.username = email;
        this.accessToken = accessToken;
        this.userRole=userRole;
    }

    // getters and setters are not shown...
}