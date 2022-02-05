package com.wzc.shoppingserver.entity;

import com.wzc.shoppingserver.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class MallGoodsSpecifications extends BaseEntity implements Serializable {

    @Column(name = "goods_price", nullable = false, columnDefinition = "int comment '商品价格 分'")
    private Integer goodsPrice;


    @Column(name = "reality_price", nullable = false, columnDefinition = "int comment '商品售价 分'")
    private Integer realityPrice;

    @Column(name = "goods_count", nullable = false, columnDefinition = "int comment '商品库存'")
    private Integer goodsCount;

    @Column(name = "goods_unit", nullable = false, columnDefinition = "varchar(50) comment '商品规格'")
    private String goodsUnit;

    /**
     * 一个商品多个价格不同规格
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mall_goods_id")
    private MallGoods mallGoods;
}
