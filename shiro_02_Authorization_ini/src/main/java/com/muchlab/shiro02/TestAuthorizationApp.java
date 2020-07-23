package com.muchlab.shiro02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.Arrays;
import java.util.List;


public class TestAuthorizationApp {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        String username = "sunqi";
        String password = "123456";
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            //用户进行登录
            subject.login(token);
            System.out.println("认证成功");
        }catch (AuthenticationException e){
            System.out.println("认证失败");
        }
        //isAuthenticated：用户是否通过验证
        System.out.println("是否认证："+subject.isAuthenticated());
        if (subject.isAuthenticated()){
            System.out.println("==========权限判断==========");
            boolean permitted = subject.isPermitted("user:query");
            System.out.println("是否有user:query的权限:"+permitted);
            boolean[] permitted1 = subject.isPermitted("user:query", "user:add", "user:export", "user:delete");
            for (boolean b : permitted1) {
                System.out.println(b);
            }
            System.out.println("==========角色判断==========");
            //判断用户是否有某种角色
            boolean role2 = subject.hasRole("role2");
            System.out.println("是否有role2的角色："+role2);

            List<String> roles = Arrays.asList("role1", "role2", "role3", "role4");
            //判断用户是否有角色集合里的角色
            boolean[] hasRoles = subject.hasRoles(roles);
            for (boolean hasRole : hasRoles) {
                System.out.println(hasRole);
            }
        }
        //用户登出
        subject.logout();
        System.out.println("是否认证："+subject.isAuthenticated());
    }
}
