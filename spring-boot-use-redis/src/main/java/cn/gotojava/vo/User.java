package cn.gotojava.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String name;        // 名字
    private Integer age;        // 年龄
    private String address;     // 地址

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
