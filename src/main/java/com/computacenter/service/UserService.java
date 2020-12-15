package com.computacenter.service;


import com.computacenter.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
