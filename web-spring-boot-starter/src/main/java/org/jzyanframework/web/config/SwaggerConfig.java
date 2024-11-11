package org.jzyanframework.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * <p>
 * swagger2.0 config
 * </p>
 *
 * @author jzyan
 * @since 2022-02-28
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
@EnableSwagger2WebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    //@Bean(value = "defaultApi")
    //public Docket defaultApi() {
    //    Docket docket = new Docket(DocumentationType.SWAGGER_2)
    //            .select()
    //            // 为当前包路径
    //            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    //            .paths(PathSelectors.any())
    //            .build()
    //            .ignoredParameterTypes(ApiIgnore.class);
    //    return docket;
    //}
}
