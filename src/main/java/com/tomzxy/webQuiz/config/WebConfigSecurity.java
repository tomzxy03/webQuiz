package com.tomzxy.webQuiz.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tomzxy.webQuiz.config.security.CustomJWTDecoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class WebConfigSecurity {
        private final String[] PUBLIC_ENDPOINT = {
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
                        "/webQuiz/auth/login",
                        "/webQuiz/auth/introspect",
                        "/webQuiz/auth/logout",
                        "/webQuiz/notifications",
                        "/webQuiz/subjects"
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
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .anyRequest().authenticated()
                );



                httpSecurity.csrf(AbstractHttpConfigurer::disable);
                httpSecurity.oauth2ResourceServer(
                                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder)
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
                                                                Collectors.toList())));
        }

        // public Jwt decodeToken(String token) {
        // JwtDecoder jwtDecoder =
        // NimbusJwtDecoder.withJwkSetUri(this.signerKey).build();
        // return jwtDecoder.decode(token);
        // }
        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Cho phép Angular
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setAllowCredentials(true); // Cho phép gửi cookie/token

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration); // Áp dụng cho mọi endpoint
                return source;
        }
}