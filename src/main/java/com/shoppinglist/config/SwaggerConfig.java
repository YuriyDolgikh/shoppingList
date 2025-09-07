package com.shoppinglist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI shoppingListOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shopping List API")
                        .description("REST API to manage shopping list")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yuriy Dolgikh")
                                .url("https://github.com/YuriyDolgikh/shoppingList")
                                .email("dolgikh.yv@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
