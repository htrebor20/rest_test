package com.user.service;

import com.user.entities.User;


import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();



    Optional<User> findById(Long id);
    void save(User user);
    void deleteById(Long id);
}
