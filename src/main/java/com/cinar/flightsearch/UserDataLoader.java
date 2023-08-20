package com.cinar.flightsearch;


import com.cinar.flightsearch.model.User;
import com.cinar.flightsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("password1"));
        Set<String> roles1 = new HashSet<>();
        roles1.add("USER");
        user1.setRoles(roles1);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("password2"));
        Set<String> roles2 = new HashSet<>();
        roles2.add("ADMIN");
        user2.setRoles(roles2);
        userRepository.save(user2);
    }
}

