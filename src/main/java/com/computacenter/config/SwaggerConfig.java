package com.computacenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){

        Profiles profiles = Profiles.of("devheroku");

        //does our environment match the specified env?  devheroku or devlocal or pro?
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //devheroku => enable swagger
                //others => disable swagger
                .enable(flag)
                .groupName("Weijia")
                .select()
                //.apis(RequestHandlerSelectors.any())
                //scan package
                .apis(RequestHandlerSelectors.basePackage("com.computacenter.controller"))
                //filter
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(){

        Contact contact = new Contact("Weijia", "http://weijiashome.com", "Ivanaluo1994@gmail.com");

        return new ApiInfo(
                "Weijia's Api Documentation",
                "Docs for Weijia's contact app.",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }
}
