package com.mitrais.rms.service;

import com.mitrais.rms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User login(String username, String password);
    List<User> getAllUser();
    Optional<User> getById(Long id);
    boolean saveOrUpdate(User user);
    boolean deleteUser(Long id);
    void validate(User user);
}
