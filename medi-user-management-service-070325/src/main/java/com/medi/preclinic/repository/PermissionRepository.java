package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
