package com.quotemedia.interview.quoteservice.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quotemedia.interview.quoteservice.bean.UserBean;
import com.quotemedia.interview.quoteservice.exception.UsernameExistException;
import com.quotemedia.interview.quoteservice.model.Users;
import com.quotemedia.interview.quoteservice.repository.UserRepository;
import com.quotemedia.interview.quoteservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails findById(String username) {

        Optional<Users> user = userRepository.findById(username);

        return user.map(UserBean::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username : " + username + " not found!"));
    }

    @Override
    public Map<String, Object> create(Users userCreate) {
        logger.info("UserServiceImpl | create | START");

        Map<String, Object> map = new HashMap<>();
        try {

            if (userCreate == null) {
                logger.error("create | userDto is null");

                map.put("status", HttpStatus.BAD_REQUEST);

                return map;
            }

            if (userRepository.existsById(userCreate.getUsername())) {
                logger.error("create | userDto is exists");

                map.put("status", HttpStatus.BAD_REQUEST);

                return map;
            }

            logger.error("create | username : " + userCreate.getUsername());

            Users user = Users.builder()
                    .username(userCreate.getUsername())
                    .password(passwordEncoder.encode(userCreate.getPassword()))
                    .role(userCreate.getRole())
                    .build();

            Users newUser = userRepository.save(user);

            map.put("user", newUser);
            map.put("status", HttpStatus.CREATED);

        } catch (Exception ex) {
            logger.error("UserServiceImpl | create | error: " + ex.getMessage());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return map;
    }
}
