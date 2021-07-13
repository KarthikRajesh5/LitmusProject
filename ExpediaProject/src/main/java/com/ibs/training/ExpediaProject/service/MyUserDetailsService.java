package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.entity.UserEntity;
import com.ibs.training.ExpediaProject.principal.UserPrincipal;
import com.ibs.training.ExpediaProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = repo.findByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("User 404");
        return new UserPrincipal(user);
    }


}