package cn.gotojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "s_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;    // 用户姓名

    @Column(name = "user_age")
    private String userAge;     // 用户年龄

    @Column(name = "user_address")
    private String userAddress; // 用户地址

    @Column(name = "user_email")
    private String userEmail;   // 用户email

    @Column(name = "user_sex")
    private Integer userSex;    // 用户性别 0男/1女

    @Column(name = "user_phone")
    private String userPhone;   // 用户手机
}
