package com.muchlab.shiro03.realm;

import com.muchlab.shiro03.entitty.User;
import com.muchlab.shiro03.service.UserService;
import com.muchlab.shiro03.service.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

public class UserRealm extends AuthenticatingRealm {
    private UserService userService = new UserServiceImpl();
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token传入的用户名
        String username = token.getPrincipal().toString();
        //token传入的密码
        Object credentials = token.getCredentials();
        System.out.println(username);
        System.out.println(credentials);
        /**
         * shiro是根据用户名把用户对象查询出来。在来做密码的匹配
         */
        User user = userService.queryUserByName(username);
        //如果查询到用户，则进行认证逻辑
        if (user!=null){
            /**
             *进行密码匹配，并将认证后的结果返回
             * 参数说明：
             * 参数1：可以传入任意对象，一般为用户名或用户对象
             * 参数2：从数据库中查询出来的密码
             * 参数3：当前的类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPwd(), this.getName());
            return info;
        }else{
            //当返回null，即代表用户不存在时，shiro会抛出UnknowAccountException异常
            return null;
        }

    }
}
