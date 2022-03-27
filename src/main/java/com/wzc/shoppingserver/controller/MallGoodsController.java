package com.wzc.shoppingserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sipios.springsearch.anotation.SearchSpec;
import com.wzc.shoppingserver.entity.AppUser;
import com.wzc.shoppingserver.entity.MallGoods;
import com.wzc.shoppingserver.repository.MallGoodsRepository;
import com.wzc.shoppingserver.service.HttpAPIService;
import lombok.var;
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

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/mall-goods")
public class MallGoodsController {

    private MallGoodsRepository mallGoodsRepository;

    @Autowired
    public MallGoodsController(MallGoodsRepository mallGoodsRepository){
        this.mallGoodsRepository = mallGoodsRepository;
    }

    @Resource
    private HttpAPIService httpAPIService;

    @GetMapping("/httpclient")
    public String test() throws Exception {
//        JSONArray cmsDocuments  = new JSONArray();
//        var cms1 = new JSONObject();
//        cms1.put("ctHeaderId","34229a4ca9b7e7caa3f7f0eb3fcd93b0");
//        cms1.put("documentId","1");
//        cms1.put("downLoadUrl","https://cmstest.longi.com/cmsfileupload/cm/upman/fileDownload?fileId=B059413CE2CB4B29A44872EA395D3727&downloadType=FILE");
//        cms1.put("fileExtension","docx");
//        cms1.put("name","第一页.docx");
//        cmsDocuments.add(cms1);
//
//        var signers = new JSONArray();
//        var signer1 = new JSONObject();
//        signer1.put("ctHeaderId","34229a4ca9b7e7caa3f7f0eb3fcd93b0");
//        signer1.put("email","154606473@qq.com");
//        signer1.put("name","Frank Jiang");
//        signer1.put("recipientId","1");
//        signer1.put("routingOrder","1");
//        signers.add(signer1);
//
//        JSONObject data = new JSONObject();
//        data.put("appUserId","167917");
//        data.put("cmsDocuments",cms1);
//        data.put("ctHeaderId","34229a4ca9b7e7caa3f7f0eb3fcd93b0");
//        data.put("emailSubject","自定义邮件标题");
//        data.put("signers",signers);
//        data.put("startingView","tagging");
        String data = "{\n" +
                "    \"appUserId\": \"167917\",\n" +
                "    \"cmsDocuments\": [\n" +
                "        {\n" +
                "            \"ctHeaderId\": \"34229a4ca9b7e7caa3f7f0eb3fcd93b0\",\n" +
                "            \"documentId\": \"1\",\n" +
                "            \"downLoadUrl\": \"https://cmstest.longi.com/cmsfileupload/cm/upman/fileDownload?fileId=B059413CE2CB4B29A44872EA395D3727&downloadType=FILE\",\n" +
                "            \"fileExtension\": \"docx\",\n" +
                "            \"name\": \"第一页.docx\",\n" +
                "            \"page\": 0,\n" +
                "            \"size\": 0,\n" +
                "            \"sqlMap\": {}\n" +
                "        },\n" +
                "        {\n" +
                "            \"ctHeaderId\": \"34229a4ca9b7e7caa3f7f0eb3fcd93b0\",\n" +
                "            \"documentId\": \"2\",\n" +
                "            \"downLoadUrl\": \"https://cmstest.longi.com/cmsfileupload/cm/upman/fileDownload?fileId=8A33CCE0D1BB451C84E4D3B88F2F21F5&downloadType=FILE\",\n" +
                "            \"fileExtension\": \"docx\",\n" +
                "            \"name\": \"第二页.docx\",\n" +
                "            \"page\": 0,\n" +
                "            \"size\": 0,\n" +
                "            \"sqlMap\": {}\n" +
                "        }\n" +
                "    ],\n" +
                "    \"ctHeaderId\": \"34229a4ca9b7e7caa3f7f0eb3fcd93b0\",\n" +
                "    \"emailSubject\": \"自定义邮件标题\",\n" +
                "    \"signers\": [\n" +
                "        {\n" +
                "            \"ctHeaderId\": \"34229a4ca9b7e7caa3f7f0eb3fcd93b0\",\n" +
                "            \"email\": \"154606473@qq.com\",\n" +
                "            \"name\": \"Frank Jiang\",\n" +
                "            \"page\": 0,\n" +
                "            \"recipientId\": \"1\",\n" +
                "            \"routingOrder\": \"1\",\n" +
                "            \"size\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"ctHeaderId\": \"34229a4ca9b7e7caa3f7f0eb3fcd93b0\",\n" +
                "            \"email\": \"66938944@qq.com\",\n" +
                "            \"name\": \"Eva Xin\",\n" +
                "            \"page\": 0,\n" +
                "            \"recipientId\": \"2\",\n" +
                "            \"routingOrder\": \"2\",\n" +
                "            \"size\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"startingView\": \"tagging\"\n" +
                "}";
        var str = httpAPIService.doPost("http://10.0.10.125:8080/cms-eg/do-work-011",data);
        return str.toString();
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
        var paging = PageRequest.of(page, size, Sort.by(descend,sortBy));
        return this.mallGoodsRepository.findAll(Specification.where(specs),paging);
    }
}
