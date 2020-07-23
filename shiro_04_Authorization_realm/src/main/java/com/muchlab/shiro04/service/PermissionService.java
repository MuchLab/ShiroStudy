package com.muchlab.shiro04.service;

import java.util.List;

public interface PermissionService {
    List<String> queryPermissionsByName(String name);
}
