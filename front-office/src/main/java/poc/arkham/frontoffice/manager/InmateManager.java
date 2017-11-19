package poc.arkham.frontoffice.manager;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poc.arkham.treatment.api.resource.InmatesResource;

@Component
public class InmateManager {

    private RestTemplate restTemplate;

    public InmateManager(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        // from https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/html/boot-features-restclient.html
    }

    public InmatesResource fetchInmates() {
        String url = UriComponentsBuilder.fromHttpUrl("http://treatment-api:8080/inmates").toUriString();
        return restTemplate.getForObject(url, InmatesResource.class);
    }

}
