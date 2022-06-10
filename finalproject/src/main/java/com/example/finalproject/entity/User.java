package com.example.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(name = "verification_code", updatable = false)
    private String verificationCode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;
}
