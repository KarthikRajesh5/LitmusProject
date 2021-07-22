package com.ibs.training.ExpediaProject.service;


import com.ibs.training.ExpediaProject.dto.UserRegistrationDto;
import com.ibs.training.ExpediaProject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity save(UserRegistrationDto registrationDto);
    public String getUser();
}