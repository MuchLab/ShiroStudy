package com.muchlab.shiro04;

import com.muchlab.shiro04.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAuthorizationApp {
    public static void main(String[] args) {
        String username = "sunqi";
        String password = "123456";
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        DefaultSecurityManager instance = (DefaultSecurityManager)factory.getInstance();
        //创建UserRealm
        UserRealm userRealm = new UserRealm();
        //给instance注入userRealm
        instance.setRealm(userRealm);
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            //用户进行登录
            subject.login(token);
            System.out.println("认证成功");
            //principal代表身份，它就是在UserRealm中的SimpleAuthenticationInfo构造器的第一个参数对象
            Object principal = subject.getPrincipal();
            System.out.println(principal);
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不正确");
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
        }
        System.out.println("是否认证成功:" + subject.isAuthenticated());
        if (subject.isAuthenticated()){
            //判断权限
            boolean permitted = subject.isPermitted("user:query");
            System.out.println("是否有user:query的权限:" + permitted);
            String[] permissions = {"user:add", "user:export", "user:delete"};
            boolean[] permitted1 = subject.isPermitted(permissions);
            for (boolean b : permitted1) {
                System.out.println(b);
            }
            //判断角色
            boolean role1 = subject.hasRole("role1");
            System.out.println("是否有role1的角色:" + role1);
            List<String> list = Arrays.asList("role2", "role3", "role4");
            boolean[] booleans = subject.hasRoles(list);
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }
        }
        subject.logout();
        System.out.println("是否认证成功:" + subject.isAuthenticated());
    }
}
