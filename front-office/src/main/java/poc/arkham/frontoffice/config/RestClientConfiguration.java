package poc.arkham.frontoffice.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = RestClientConfiguration.RibbonConfiguration.class)
@RibbonClient(name = "treatment-api")
@EnableRetry
public class RestClientConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Configuration
    class RibbonConfiguration {

        //@Bean
        //public IPing ribbonPing() {
        //    return new PingUrl(false, "/health");
        //}

        @Bean
        public IRule ribbonRule() {
            // return new AvailabilityFilteringRule(); // some NPE logged, maybe because it needs a circuit breaker
            return new RoundRobinRule(); // quite fast !
        }
    }
}
