package com.muchlab.shiro04.service.impl;

import com.muchlab.shiro04.service.RoleService;

import java.util.Arrays;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> queryRolesByName(String name) {
        return Arrays.asList("role1", "role2", "role3");
    }
}
