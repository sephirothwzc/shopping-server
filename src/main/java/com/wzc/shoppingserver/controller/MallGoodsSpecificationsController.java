package com.wzc.shoppingserver.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import com.wzc.shoppingserver.entity.MallGoods;
import com.wzc.shoppingserver.entity.MallGoodsSpecifications;
import com.wzc.shoppingserver.repository.MallGoodsSpecificationsRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mall-goods-specifications")
public class MallGoodsSpecificationsController {

    private MallGoodsSpecificationsRepository repository;

    @Autowired
    public MallGoodsSpecificationsController(MallGoodsSpecificationsRepository repository){
        this.repository = repository;
    }

    @GetMapping("/page")
    public Page<MallGoodsSpecifications> searchBy(@SearchSpec Specification<MallGoodsSpecifications> specs,
                                                  @RequestParam(value = "page",defaultValue = "0") int page,
                                                  @RequestParam(value="size",defaultValue = "20") int size,
                                                  @RequestParam(value="sort",defaultValue = "createdAt") String sortBy,
                                                  @RequestParam(value="descend",defaultValue = "DESC") Sort.Direction descend) {
        var paging = PageRequest.of(page, size, Sort.by(descend,sortBy));
        return this.repository.findAll(Specification.where(specs),paging);
    }
}
