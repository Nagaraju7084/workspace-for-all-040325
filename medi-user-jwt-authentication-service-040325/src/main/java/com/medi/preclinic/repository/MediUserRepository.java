package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medi.preclinic.model.MediUser;

public interface MediUserRepository extends JpaRepository<MediUser, Integer>{
	
	@Query(name = "findUserByEmail", value = "from MediUser user where user.email=:email")
	public MediUser findUserByEmail(@Param("email") String email);

}
