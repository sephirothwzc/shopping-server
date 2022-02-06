package com.wzc.shoppingserver.repository;

import com.wzc.shoppingserver.entity.MallGoodsSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mall-goods-specifications")
public interface MallGoodsSpecificationsRepository extends JpaRepository<MallGoodsSpecifications, Long>, JpaSpecificationExecutor<MallGoodsSpecifications> {
}
