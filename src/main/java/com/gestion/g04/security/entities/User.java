package com.gestion.g04.security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private  String userId;
    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;



}
