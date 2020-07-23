package com.muchlab.shiro04.entitty;

import java.util.ArrayList;
import java.util.List;

public class ActiveUser {
    private User user = new User();
    private List<String> roles = new ArrayList<>();
    private List<String> permissions = new ArrayList<>();

    public ActiveUser() {
    }

    public ActiveUser(User user, List<String> roles, List<String> permissions) {
        this.user = user;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
