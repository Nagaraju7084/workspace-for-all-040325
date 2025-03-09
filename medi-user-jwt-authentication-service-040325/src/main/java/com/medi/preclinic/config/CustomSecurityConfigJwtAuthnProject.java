package com.medi.preclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfigJwtAuthnProject extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProviderJwtAuthnProject customAuthnProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/api/**") //allow all the requests with are starting with /api
		//because the user registration shouldn't go through the security and user authentication code
		//also should not go through the security, it should be an openapi(jwt) where we should have to submit the username
		//and password and get the token 
		.permitAll(); //upto here enough, no need to add formabased and basic becuase we want go with the token based security
		//to test this, first register the user with roles, to do this,
		//we have sql script and this script will load at application startup
		//we have to add one property(below one)in application.properties file to execute the sql script automatically
		//and dump the data into database that property is
		//#if we want intialize datasource always regardless of its type
		//spring.datasource.initialization-mode=always
		//spring.datasource.data=classpath:schema.sql
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthnProvider);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
