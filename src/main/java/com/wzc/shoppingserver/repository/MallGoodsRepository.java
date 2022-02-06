package com.wzc.shoppingserver.repository;

import com.wzc.shoppingserver.entity.MallGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mall-goods")
public interface MallGoodsRepository extends JpaRepository<MallGoods, Long>, JpaSpecificationExecutor<MallGoods> {
}
