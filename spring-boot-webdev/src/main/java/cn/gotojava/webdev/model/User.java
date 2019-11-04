package cn.gotojava.webdev.model;

import lombok.Data;

@Data
public class User {
    private String userName;    // 用户姓名
    private String userAddress; // 用户地址
    private String userSex;     // 用户性别
    private Integer userAge;    // 用户年龄
}
