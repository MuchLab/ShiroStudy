package com.muchlab.shiro03.service;

import com.muchlab.shiro03.entitty.User;

import java.util.Date;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserByName(String username) {
        switch (username){
            case "zhangsan":
                return new User(1, username, "123456", new Date());
            case "lisi":
                return new User(2, username, "123456", new Date());
            case "sunqi":
                return new User(3, username, "123456", new Date());
        }
        return null;
    }
}
