package cn.gotojava.springboot.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Dog {

    private String dogName; // 狗名字
    private Integer dogAge; // 狗年龄
    private Boolean dogSex; // 狗性别  true公   false母
}
