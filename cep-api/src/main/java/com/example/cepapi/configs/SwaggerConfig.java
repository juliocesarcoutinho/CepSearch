package com.example.cepapi.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Zip Code API")
                        .version("v1")
                        .description("This is a Api the find zip code in Brazil")
                        .termsOfService("https://github.com/juliocesarcoutinho")
                        .license(new License()
                                .name("none")
                                .url("https://github.com/juliocesarcoutinho")
                        )
                );
    }
}