package com.muchlab.shiro04.realm;

import com.muchlab.shiro04.entitty.ActiveUser;
import com.muchlab.shiro04.entitty.User;
import com.muchlab.shiro04.service.PermissionService;
import com.muchlab.shiro04.service.RoleService;
import com.muchlab.shiro04.service.UserService;
import com.muchlab.shiro04.service.impl.PermissionServiceImpl;
import com.muchlab.shiro04.service.impl.RoleServiceImpl;
import com.muchlab.shiro04.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private PermissionService permissionService = new PermissionServiceImpl();

    /**
     * 认证，当使用login时会调用此逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
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
            List<String> roles = roleService.queryRolesByName(username);
            List<String> permissions = permissionService.queryPermissionsByName(username);
            ActiveUser activeUser = new ActiveUser(user, roles, permissions);
            /**
             *进行密码匹配，并将认证后的结果返回
             * 参数说明：
             * 参数1：可以传入任意对象，一般为用户名或用户对象
             * 参数2：从数据库中查询出来的密码
             * 参数3：当前的类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPwd(), this.getName());
            return info;
        }else{
            //当返回null，即代表用户不存在时，shiro会抛出UnknowAccountException异常
            return null;
        }
    }

    /**
     * 授权，即给用户添加权限或角色的逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加权限
//        info.addStringPermission("user:query");
//        Collection<String> permissions = new ArrayList<>();
//        Collections.addAll(permissions, "user:add", "user:export");
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        List<String> permissions = activeUser.getPermissions();
        if (permissions.size()>0)
            info.addStringPermissions(permissions);
        //添加角色
//        info.addRole("role1");
//        Collection<String> list = new ArrayList<>();
//        Collections.addAll(list,  "role2", "role3");
        List<String> roles = activeUser.getRoles();
        if (roles.size()>0)
            info.addRoles(roles);

        return info;
    }
}
