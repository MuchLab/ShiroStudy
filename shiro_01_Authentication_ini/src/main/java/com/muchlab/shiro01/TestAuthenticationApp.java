package com.muchlab.shiro01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAuthenticationApp {
    //日志输出工具
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthenticationApp.class);
    public static void main(String[] args) {
        log.info("My First Shiro App");
        String password = "123456";
        String username = "zhangsan";
        //创建一个安全管理器的工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //使用工厂创建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //把当前的安全管理器绑定到当前线程
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //给主体对象设置用户名和密码
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //认证
        try{
            subject.login(token);
            log.info("认证通过");
        }catch (AuthenticationException e){
            log.info("认证不通过");
        }
    }
}
