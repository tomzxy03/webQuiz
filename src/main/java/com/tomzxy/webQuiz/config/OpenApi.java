package com.tomzxy.webQuiz.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("!prod")
public class OpenApi {
    @Bean
    public GroupedOpenApi publicApi(@Value("${openapi.service.api-docs}") String apiDocs){ //@Value("${openapi.service.api-docs}") String apiDocs
        return GroupedOpenApi.builder()
                .group("apiDocs")
                .packagesToScan("com.tomzxy.webQuiz.controller")
                .build();
    }

//    @Bean
//    public OpenAPI openApi( @Value("${openapi.service.title}") String title,
//                            @Value("${openapi.service.version}") String version,
//                            @Value("${openapi.service.server}") String serverUrl){
//        return new OpenAPI()
//                .servers(List.of(new Server().url(serverUrl)))
//                .info(new Info().title(title)
//                        .description("API documents")
//                        .version(version)
//                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
//    }
}
