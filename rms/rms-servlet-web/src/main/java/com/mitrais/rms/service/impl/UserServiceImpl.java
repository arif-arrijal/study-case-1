package com.mitrais.rms.service.impl;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User login(String username, String password) {
        this.validate(new User(username, password));

        Optional<User> user = userDao.findByUserName(username);
        if (!user.isPresent()) throw new RuntimeException("User " + username +" is not found");
        if (!user.get().getPassword().equals(password)) throw new RuntimeException("Password incorrect");

        return user.get();
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.find(id);
    }

    @Override
    public boolean saveOrUpdate(User user) {
        this.validate(user);

        if (user.getId() == null) {
            return userDao.save(user);
        } else {
            return userDao.update(user);
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        boolean isDeleted = false;

        Optional<User> deletedUser = userDao.find(id);
        if (deletedUser.isPresent()) {
            User user = deletedUser.get();
            isDeleted = userDao.delete(user);
        }

        return isDeleted;
    }

    @Override
    public void validate(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) throw new RuntimeException("Username cannot be empty");
        if (user.getPassword() == null || user.getPassword().isEmpty()) throw new RuntimeException("Password cannot be empty");
        if (user.getId() != null) {
            Optional<User> existUser = userDao.find(user.getId());
            if (!existUser.isPresent()) throw new RuntimeException("User with id " + user.getId() + " not found");
        }
    }

    private static class SingletonHelper
    {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance()
    {
        return SingletonHelper.INSTANCE;
    }
}
