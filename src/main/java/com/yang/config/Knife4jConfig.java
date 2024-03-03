package com.yang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@Configuration
public class Knife4jConfig {
    @Bean
    Docket studentDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学生端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yang.controller.student_controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    Docket adminDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yang.controller.admin_controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
