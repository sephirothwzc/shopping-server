package com.wzc.shoppingserver.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wzc.shoppingserver.entity.AppUser;
import com.wzc.shoppingserver.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Specification<AppUser> spc = (root, criteriaQuery, criteriaBuilder) -> {
            // return criteriaBuilder.and(new Predicate[]{p1, p2});
            return criteriaBuilder.equal(root.get("phone"),username);  //两个return效果一样
        };
        Optional<AppUser> user = appUserRepository.findOne(spc);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //我这里弄一个简单的权限集合，真实的开发中是要查询数据库的
        List<GrantedAuthority> atuh = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,guest");

        //从数据库查出的用户信息赋值给MyUserDetails对象 ,MyUserDetails对象继承了security框架中user对象
        return new User(user.get().getPhone(),user.get().getPassword(),atuh);
    }

}