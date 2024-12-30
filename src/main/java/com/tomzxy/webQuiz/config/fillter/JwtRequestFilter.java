//package com.tomzxy.webQuiz.config.fillter;
//
//import com.tomzxy.webQuiz.config.WebConfigSecurity;
//import com.tomzxy.webQuiz.util.AuthorityUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final WebConfigSecurity webConfigSecurity;
//    public JwtRequestFilter(WebConfigSecurity webConfigSecurity) {
//        this.webConfigSecurity = webConfigSecurity;
//    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String token =null;
//        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
//            token= authorizationHeader.substring(7);
//        }
//        if(token!= null){
//            Jwt jwt = webConfigSecurity.decodeToken(token);
//            Map<String, List<String>> permissions  = webConfigSecurity.extractPermissions(jwt);
//            Collection<? extends GrantedAuthority> authorities = AuthorityUtil.mapToGrantedAuthorities(permissions);
//            SecurityContextHolder.getContext().setAuthentication(
//                    new JwtAuthenticationToken(jwt, authorities,null)
//            );
//        }
//        filterChain.doFilter(request,response);
//    }
//}
