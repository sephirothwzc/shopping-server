package com.wzc.shoppingserver.entity;

import com.alibaba.fastjson.JSON;
import com.wzc.shoppingserver.base.BaseEntity;
import com.wzc.shoppingserver.base.HashMapConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class MallGoods extends BaseEntity implements Serializable {
    @Column(columnDefinition = "varchar(50) comment '商品编号'")
    private String code;

    @Column(name = "goods_name", nullable = false, columnDefinition = "varchar(50) comment '商品名称'")
    private String goodsName;

    @Column(name = "goods_image", columnDefinition = "varchar(50) comment '商品图片'")
    private String goodsImage;

    @Column(name = "goods_type", columnDefinition = "varchar(50) comment '商品分类'")
    private String goodsType;

    @Column(name = "goods_params", columnDefinition = "json comment '商品参数'")
    @Convert(converter = HashMapConverter.class)
    private JSON goodsParams;

    @Column(name = "goods_details", columnDefinition = "LongText comment '商品详情'")
    private String goodsDetails;

    @Column(name = "goods_tag", columnDefinition = "json comment '商品标签'")
    @Convert(converter = HashMapConverter.class)
    private JSON goodsTag;

    /**
     * 一个商品多个价格不同规格
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MallGoodsSpecifications> mallGoodsSpecifications;
}
