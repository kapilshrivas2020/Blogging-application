package com.apidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidev.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
