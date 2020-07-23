package com.muchlab.shiro04.service;


import com.muchlab.shiro04.entitty.User;

public interface UserService {
    User queryUserByName(String username);
}
