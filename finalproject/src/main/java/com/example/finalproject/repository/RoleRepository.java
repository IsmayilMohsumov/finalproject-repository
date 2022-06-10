package com.example.finalproject.repository;

import com.example.finalproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role ,Integer> {
    public Role findByRole(String role);


}