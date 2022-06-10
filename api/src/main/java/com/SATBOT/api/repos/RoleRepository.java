package com.SATBOT.api.repos;

import com.SATBOT.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role ,Integer> {
    public Role findByRole(String role);


}