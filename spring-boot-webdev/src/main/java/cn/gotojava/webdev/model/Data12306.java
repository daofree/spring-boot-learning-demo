package cn.gotojava.webdev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Data_12306实体类
 */
@Data
@Entity
@Table(name = "data_12306") // 指定映射表
public class Data12306 implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;    // 主键编号
    @Column(nullable = false,name = "data_mail") private String dataMail;  // 邮箱
    @Column(nullable = false,name = "password") private String password;    // 密码
    @Column(nullable = false,name = "name") private String name;    // 姓名
    @Column(nullable = false,name = "id_card") private String idCard;  //
    @Column(nullable = false,name = "username") private String username;
    @Column(nullable = false,name = "mobile") private String mobile;  // 手机号
    @Column(nullable = false,name = "e_mail") private String eMail;   // 邮箱

}
