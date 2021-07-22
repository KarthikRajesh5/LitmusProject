package com.ibs.training.ExpediaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class ExpediaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpediaProjectApplication.class, args);
	}

	//get logged in username
	public String getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

}
