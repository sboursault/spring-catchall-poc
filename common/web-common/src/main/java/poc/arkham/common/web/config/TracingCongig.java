package poc.arkham.common.web.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingCongig {

    /*
     * Defines the logs to send to zipkin
     */
    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
