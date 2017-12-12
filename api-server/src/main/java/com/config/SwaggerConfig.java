package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .paths(PathSelectors.regex("/operations/*.*")).build();//.pathMapping("/platform"); // 在这里可以设置请求的统一前缀
        

    }


    private static springfox.documentation.service.ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("测试暂用api").description("")
                .contact(new Contact("Cyouakira", "", "510489788@qq.com")).license("gitHub Link")
                .licenseUrl("https://github.com/Cyouakira/api-server").version("1.0").build();
    }

}