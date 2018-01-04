package poc.arkham.research.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import poc.arkham.common.apiserver.config.SwaggerConfig;

/*
 * Sprint boot appplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(SwaggerConfig.class)
@ComponentScan({"poc.arkham.common.web", "poc.arkham.research"}) // TODO: shouldn't need to scan poc.arkham.common
public class ResearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchApiApplication.class, args);
    }

}
