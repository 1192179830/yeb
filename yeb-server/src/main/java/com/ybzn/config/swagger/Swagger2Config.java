package com.ybzn.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Hugo
 * @time 2021/1/22
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createDocket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ybzn.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("云E办接口文档")
                .description("云E办接口文档")
                .contact(new Contact("Hugo","http:localhost:8091/docs.html","1192179830@qq.com"))
                .version("2.0")
                .build();

    }

    private List <ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result =new ArrayList <>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts(){
        List<SecurityContext> list =new ArrayList <>();
        list.add(getContextByPath("/hello/.*"));
        return  list;
    }

    private SecurityContext getContextByPath (String pathRegx) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegx))
                .build();
    }

    private List<SecurityReference> defaultAuth () {
        List<SecurityReference> list =new ArrayList <>();
        AuthorizationScope authorizationScope =new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes =new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
         list.add(new SecurityReference("Authorization", authorizationScopes));
         return list;
    }


}
