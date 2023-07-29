package com.bharat.AnimeAPI.security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "animeUserAuth")
public class UserSaveWrapper {
    @Id
    private String email;
    private String username;
    private String password;
}
