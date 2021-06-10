package com.lbb.vuehousespringboot.shiro;

import com.lbb.vuehousespringboot.entity.TbUser;
import com.lbb.vuehousespringboot.service.TbUserService;
import com.lbb.vuehousespringboot.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TbUserService userService;

    /**
     * 使realm支持jwt校验
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 登录认证校验
     * 通过jwt获取到用户信息，判断用户的状态，最后异常就抛出对应的异常信息，否者封装成SimpleAuthenticationInfo返回给shiro。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获得token
        JwtToken jwtToken =(JwtToken) authenticationToken;
        //获得userid
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        //获得用户
        TbUser user = userService.getById(Long.valueOf(userId));
        //判断user是否存在，并且是否被锁定
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus()==-1){
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile=new AccountProfile();
        //工具类直接复制
        BeanUtils.copyProperties(user,profile);

        System.out.println("-----------");
        //一些可以公开的信息,返回给shrio
        //profile封装可公开的信息
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
