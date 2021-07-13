package com.ibs.training.ExpediaProject.service;


import com.ibs.training.ExpediaProject.dto.UserRegistrationDto;
import com.ibs.training.ExpediaProject.entity.UserEntity;

public interface UserService {

    UserEntity save(UserRegistrationDto registrationDto);
}