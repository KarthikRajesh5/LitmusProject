package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.dto.UserRegistrationDto;
import com.ibs.training.ExpediaProject.entity.UserEntity;
import com.ibs.training.ExpediaProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserRegistrationDto registrationDto) {

        UserEntity userEntity = new UserEntity(registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                registrationDto.getPhoneNumber());

        return userRepository.save(userEntity);
    }
}

