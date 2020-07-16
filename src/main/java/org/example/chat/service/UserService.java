package org.example.chat.service;

import org.example.chat.model.RoleEnum;
import org.example.chat.model.StateEnum;
import org.example.chat.model.User;
import org.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    public void upDate(User user){
        System.out.println(user.getHashPassword());
        String hashPassword = passwordEncoder.encode(user.getHashPassword());
        user.setHashPassword(hashPassword);
        System.out.println(user.getHashPassword());
        userRepository.save(user);

    }
}
