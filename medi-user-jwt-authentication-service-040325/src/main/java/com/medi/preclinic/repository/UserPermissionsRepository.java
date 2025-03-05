package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.model.UserPermission;

public interface UserPermissionsRepository extends JpaRepository<UserPermission, Integer> {

}
