package com.muchlab.shiro04.service.impl;

import com.muchlab.shiro04.service.PermissionService;

import java.util.Arrays;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> queryPermissionsByName(String name) {
        return Arrays.asList("user:query", "user:add", "user:export");
    }
}
