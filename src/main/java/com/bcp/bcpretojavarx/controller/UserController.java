package com.bcp.bcpretojavarx.controller;

import com.bcp.bcpretojavarx.config.AppConfiguration;
import com.bcp.bcpretojavarx.controller.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController
{
    @PostMapping("user")
    public User login(@RequestParam("user") final String username,
                      @RequestParam("password") final String pwd) {

        String token = getJWTToken(username);
        User user = new User();
        user.setUser(username);
        user.setToken(token);
        return user;

    }

    private String getJWTToken(final String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("jcsoftJWT")
                .setSubject(username)
                .claim("authorities",
                       grantedAuthorities.stream()
                               .map(GrantedAuthority::getAuthority)
                               .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                          AppConfiguration.SECRET_KEY.getBytes()).compact();

        return "Bearer " + token;
    }
}
