package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
