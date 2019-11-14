package cn.gotojava.vo;

import lombok.Data;

/**
 * 用户对象实体
 */
@Data
public class User {
    private String username;    // 用户名
    private String address;     // 地址
    private String age;         // 年龄
    private String email;       // email
}
