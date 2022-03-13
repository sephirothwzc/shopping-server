package com.wzc.shoppingserver.base;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "int comment 'id'")
    private Long id;


    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP comment '创建时间'")
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
    private LocalDateTime updatedAt;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(name = "created_by", nullable = true, columnDefinition = "int comment '创建人id'")
    private Long createdBy = Long.valueOf(1);

    /**
     * 更新人
     */
    @LastModifiedBy
    @Column(name = "updated_by", nullable = true, columnDefinition = "int comment '更新人id'")
    private Long updatedBy;

    @Column(nullable = true, columnDefinition = "varchar(255) comment '备注'")
    private String remark;

    @Column(name = "enable_flag", nullable = false, columnDefinition = "int DEFAULT 1 comment '状态'")
    private Integer enableFlag = 1;
}
