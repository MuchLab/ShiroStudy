package com.muchlab.shiro04.service;

import java.util.List;

public interface RoleService {
    List<String> queryRolesByName(String name);
}
