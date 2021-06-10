package com.lbb.vuehousespringboot.util;

import com.lbb.vuehousespringboot.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

//subject登陆成功后，下一次请求如何知道是那个用户的请求呢？

/**
 * 每次请求都会重新设置Session和Principals,看到这里大概就能猜到：如果是web工程，
 * 直接从web容器获取httpSession，
 * 然后再从httpSession获取Principals，本质就是从cookie获取用户信息，
 * 然后每次都设置Principal，这样就知道是哪个用户的请求，并只得到这个用户有没有人认证成功，--本质：依赖于浏览器的cookie来维护session的
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
