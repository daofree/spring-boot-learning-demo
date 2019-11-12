package cn.gotojava.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String name;        // 名字
    private String age;        // 年龄
    private String address;     // 地址
    private String email;       // email

    public User(String email, String name, String age, String address) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
