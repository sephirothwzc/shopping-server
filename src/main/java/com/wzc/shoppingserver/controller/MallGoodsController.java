package com.wzc.shoppingserver.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import com.wzc.shoppingserver.entity.AppUser;
import com.wzc.shoppingserver.entity.MallGoods;
import com.wzc.shoppingserver.repository.MallGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mall-goods")
public class MallGoodsController {

    private MallGoodsRepository mallGoodsRepository;

    @Autowired
    public MallGoodsController(MallGoodsRepository mallGoodsRepository){
        this.mallGoodsRepository = mallGoodsRepository;
    }

    /**
     * http://localhost:8080/api/mall-goods/page?search=goodsName:'123'&page=1&size=30
     * https://github.com/sipios/spring-search
     */
    @GetMapping("/page")
    public Page<MallGoods> searchBy(@SearchSpec Specification<MallGoods> specs,
                                    @RequestParam(value = "page",defaultValue = "0") int page,
                                    @RequestParam(value="size",defaultValue = "20") int size,
                                    @RequestParam(value="sort",defaultValue = "createdAt") String sortBy,
                                    @RequestParam(value="descend",defaultValue = "DESC") Sort.Direction descend) {
        Pageable paging = PageRequest.of(page, size, Sort.by(descend,sortBy));
        return this.mallGoodsRepository.findAll(Specification.where(specs),paging);
    }
}
