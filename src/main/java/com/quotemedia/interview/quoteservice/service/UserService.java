package com.quotemedia.interview.quoteservice.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.quotemedia.interview.quoteservice.model.Users;

public interface UserService {
    public UserDetails findById(String username);

    public Map<String, Object> create(Users userCreate);
}
