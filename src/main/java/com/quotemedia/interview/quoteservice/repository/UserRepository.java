package com.quotemedia.interview.quoteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quotemedia.interview.quoteservice.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {

}
