package poc.arkham.common.apiserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poc.arkham.common.web.config.ApplicationProperties;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    ApplicationProperties properties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .host(properties.getBaseUrl().replace("https://", "").replace("http://", ""))
          .select()
          .apis(
              or(
                  basePackage("poc.arkham.treatment.api.controller"),
                  basePackage("poc.arkham.research.api.controller")))
          .paths(any())
          .build();
    }

}