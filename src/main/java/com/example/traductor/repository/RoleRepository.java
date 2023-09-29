package com.example.traductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.traductor.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
