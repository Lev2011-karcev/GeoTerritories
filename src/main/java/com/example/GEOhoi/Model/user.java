package com.example.GEOhoi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String providerId;
    private String email;
    private String name;
    private String avatar;

    public user(String provider, String providerId, String email, String name, String avatar) {
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }
}