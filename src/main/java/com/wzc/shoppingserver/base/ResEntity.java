package com.wzc.shoppingserver.base;

import lombok.Data;

@Data
public class ResEntity<T> {
    public ResEntity(String msg) {
        this.msg = msg;
        this.code = "500";
    }

    public ResEntity(T t) {
        this.code = "200";
        this.data = t;
    }

    private String code;

    private String msg;

    private T data;
}
