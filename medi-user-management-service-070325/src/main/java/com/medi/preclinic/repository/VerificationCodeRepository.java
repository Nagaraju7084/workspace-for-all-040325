package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {

}
