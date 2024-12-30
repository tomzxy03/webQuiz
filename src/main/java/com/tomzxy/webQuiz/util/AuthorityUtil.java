package com.tomzxy.webQuiz.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AuthorityUtil {
    public static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Map<String, List<String>> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        permissions.forEach((objectType, actions) -> {
            actions.forEach(action -> {
                authorities.add(new SimpleGrantedAuthority(objectType + ":" + action));
            });
        });
        return authorities;
    }
}
