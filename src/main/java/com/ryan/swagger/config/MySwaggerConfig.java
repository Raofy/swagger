package com.ryan.swagger.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2         //启用Swagger2
public class MySwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    /**
     * swagger的实例bean是docket
     *
     * @param environment
     * @return
     */
    @Bean
    public Docket createRestApi(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");

        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("分组1")
                .apiInfo(apiInfo())
                //.additionalModels(typeResolver.resolve(User.class))
                .enable(b)        ////配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()            // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors
                        .basePackage("com.ryan.swagger.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 配置多个分组
     *
     * @param environment
     * @return
     */
    @Bean
    public Docket createRestApi2(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");

        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("分组2")
                .apiInfo(apiInfo())
                .enable(b)        ////配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()            // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors
                        .basePackage("com.ryan.swagger.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 通过ApiInfo进行配置文档信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring boot示例接口API")
                .description("spring boot示例接口API")
                .version("1.0").build();
    }
}
