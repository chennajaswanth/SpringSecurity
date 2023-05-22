package com.example.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfigurar extends WebSecurityConfigurerAdapter {
	
	
//	@Autowired
//	DataSource dataSource;
	
    //to authenticate users in inmemory authentication
	
	  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		  auth.inMemoryAuthentication() .withUser("kaal")
	   .password("Krish").roles("USER") .and() .withUser("idk")
	    .password("idk")
	    .roles("ADMIN");
	  
	  }
	 
	//we can also authenticate users stored  in database
	

	
	
	//spring security search a bean with name getPasswordEncoder and it will use the automatic password encoding.
	@Bean
	protected PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	//to authorize the users
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/admin-map").hasRole("ADMIN")
        .antMatchers("/user-map").hasAnyRole("ADMIN", "USER")
        .antMatchers("/").permitAll()
        .and().formLogin();
	}
	
	//to logout by default
	//http://localhost:8080/login?logout
	
	
	
	
	
	
	
	

}
