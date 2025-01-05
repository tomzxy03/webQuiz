package com.tomzxy.webQuiz.config;

import com.tomzxy.webQuiz.config.security.CustomJWTDecoder;
import com.tomzxy.webQuiz.constants.EndPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class WebConfigSecurity {
    private final String[] PUBLIC_ENDPOINT =
            {
                    // Swagger
                    "/v3/api-docs/**",
                    "/swagger-ui/index.html",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger/**",
                    "/api-docs/**",
                    "/swagger-resources/**",
                    "/swagger-resources/",
                    "/configuration/ui",
                    "/configuration/security",
                    // auth
                    "/test-web/auth/token",
                    "/test-web/auth/introspect",
                    "/test-web/auth/logout"
            };


    @Autowired
    private CustomJWTDecoder jwtDecoder;

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(PUBLIC_ENDPOINT)
                        .permitAll()
                        .anyRequest().authenticated()
                        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.decoder(jwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))

        );


        return httpSecurity.build();
    }

    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");


        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    public Map<String, List<String>> extractPermissions(Jwt jwt) {
        String scope = jwt.getClaimAsString("scope"); // Tách chuỗi scope thành các phần tử
         String[] permissionsArray = scope.split(" "); // Tạo Map từ chuỗi scope
         return Arrays.stream(permissionsArray)
                 .map(permission -> permission.split("_"))
                 .collect(Collectors.groupingBy(parts -> parts[0],
                         Collectors.mapping(parts -> parts[1],
                                 Collectors.toList()) ));
    }
//    public Jwt decodeToken(String token) {
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(this.signerKey).build();
//        return jwtDecoder.decode(token);
//    }
}