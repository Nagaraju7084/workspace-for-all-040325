package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
