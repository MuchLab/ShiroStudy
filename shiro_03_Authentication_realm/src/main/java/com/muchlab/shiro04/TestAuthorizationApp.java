package com.muchlab.shiro03;

import com.muchlab.shiro03.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

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
    }
}
