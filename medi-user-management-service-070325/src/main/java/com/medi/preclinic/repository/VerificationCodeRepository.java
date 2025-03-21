package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medi.preclinic.domain.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {
	@Query("from VerificationCode vc where vc.code=:vCode")
	public VerificationCode findByVerificationCode(@Param("vCode")String verificationCode);
}
