package com.computacenter.service;


import com.computacenter.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
