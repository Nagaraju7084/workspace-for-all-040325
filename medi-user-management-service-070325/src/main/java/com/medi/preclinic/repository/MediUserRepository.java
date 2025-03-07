package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.MediUser;

public interface MediUserRepository extends JpaRepository<MediUser, Integer> {

}
