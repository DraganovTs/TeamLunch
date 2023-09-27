package com.homecode.user.service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(max = 60)
    @Column(columnDefinition = "TEXT")
    private String password;
    @ManyToMany
    private Set<Role> roles;


    public User(String username, String email, String encode) {
        this.username=username;
        this.email=email;
        this.password=encode;
    }

    public User(String username, String email, String password, Set<Role> roles) {
        this.username=username;
        this.email=email;
        this.password=password;
        this.roles=roles;
    }
}
