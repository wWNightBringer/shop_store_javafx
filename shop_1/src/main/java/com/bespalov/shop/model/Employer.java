package com.bespalov.shop.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employer {
    private String username;
    private String password;

    public Employer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employer() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
