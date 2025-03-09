package com.medi.preclinic.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.medi.preclinic.model.MediUser;
import com.medi.preclinic.repository.MediUserRepository;

@Component
public class CustomUserDetailsServiceJwtAuthnProject implements UserDetailsService {
	
	@Autowired
	private MediUserRepository mediUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MediUser databaseUser = mediUserRepository.findUserByEmail(username);
		return new User(databaseUser.getEmail(), databaseUser.getPassword(), 
		Arrays.asList(new SimpleGrantedAuthority("ROLE_" + databaseUser.getUserRole()))		
		);
	}

}
