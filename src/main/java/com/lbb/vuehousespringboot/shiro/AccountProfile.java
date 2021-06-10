package com.lbb.vuehousespringboot.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 3、而在AccountRealm我们还用到了AccountProfile，这是为了登录成功之后返回的一个用户信息的载体，
 */
@Data
//账号简况
public class AccountProfile  implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
