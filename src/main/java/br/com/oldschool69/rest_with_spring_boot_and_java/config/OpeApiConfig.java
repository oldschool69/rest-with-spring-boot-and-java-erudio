package br.com.oldschool69.rest_with_spring_boot_and_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpeApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's Java Spring Boot training")
                        .version("v1")
                        .description("REST API's Java Spring Boot training")
                        .termsOfService("my terms")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("License URL"))
                );
    }
}
