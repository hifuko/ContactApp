package com.computacenter.service.impl;


import com.computacenter.entity.User;
import com.computacenter.repository.UserRepository;
import com.computacenter.service.UserService;
import com.computacenter.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
