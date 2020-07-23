package com.muchlab.shiro03.service;

import com.muchlab.shiro03.entitty.User;

public interface UserService {
    User queryUserByName(String username);
}
