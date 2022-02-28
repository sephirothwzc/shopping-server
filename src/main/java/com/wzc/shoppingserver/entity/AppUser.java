package com.wzc.shoppingserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzc.shoppingserver.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class AppUser extends BaseEntity  implements Serializable {

    @Column(columnDefinition = "varchar(50) comment '手机号'")
    private String phone;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "varchar(200) comment '密码'")
    private String password="123456";

    @Column(nullable = false, columnDefinition = "varchar(50) comment '昵称'")
    private String nickname;

    @Column(nullable = false, columnDefinition = "varchar(50) comment '真实姓名'")
    private String realname;

    @Column(columnDefinition = "datetime comment '生日'")
    private LocalDateTime birthday;

    @Column(nullable = false, columnDefinition = "varchar(50) comment '编号'")
    private String code;

}
