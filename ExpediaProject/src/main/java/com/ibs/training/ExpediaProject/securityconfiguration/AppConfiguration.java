package com.ibs.training.ExpediaProject.securityconfiguration;

import com.ibs.training.ExpediaProject.dto.HotelBookingDTO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.entity.HotelBookingEntity;
import com.ibs.training.ExpediaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableWebSecurity
public class AppConfiguration extends WebSecurityConfigurerAdapter
{
    /*@Autowired
    private UserDetailsService userDetailsService;*/

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HotelDTO hotelDTO(){
        return new HotelDTO();
    }

    @Bean
    public HotelBookingEntity hotelBookingEntity(){
        return new HotelBookingEntity();
    }

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/login","/registration","/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/login").permitAll()
                .successForwardUrl("/")
        ;

    }


}