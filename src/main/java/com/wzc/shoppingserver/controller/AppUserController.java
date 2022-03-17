package com.wzc.shoppingserver.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import com.wzc.shoppingserver.base.ResEntity;
import com.wzc.shoppingserver.entity.AppUser;
import com.wzc.shoppingserver.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/app-user")
public class AppUserController {

    private  AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * http://127.0.0.1:8080/v1/api/app-user/?search=phone:'18554870324' AND nickname:test
     * https://github.com/sipios/spring-search
     */
    @GetMapping("/")
    public List<AppUser> searchBy(@SearchSpec Specification<AppUser> specs) {
        return appUserRepository.findAll(Specification.where(specs));
    }

    /**
     * http://127.0.0.1:8080/v1/api/app-user/login?username=18554870324&password=123456
     */
    @GetMapping("/login")
    public ResEntity<AppUser> login(@RequestParam String username, @RequestParam String password) {
        String pwd = stringToMD5(password);
        System.out.println(String.format("pwd=%s",pwd));
        AppUser appUser = appUserRepository.findOneByPhoneAndPassword(username,pwd);
        if(appUser == null) {
            return new ResEntity<AppUser>("用户名密码错误");
        }else {
            return new ResEntity<AppUser>(appUser);
        }
//        return appUserRepository.findOne(Specification{root: Root<AppUser>, query: CriteriaQuery<*>, cb: CriteriaBuilder ->
//            cb.equal(root.get<String>("phone"),username)
//        })
    }

    public String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
