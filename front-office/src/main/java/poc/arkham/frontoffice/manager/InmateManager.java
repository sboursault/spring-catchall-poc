package poc.arkham.frontoffice.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poc.arkham.treatment.api.resource.InmatesResource;

@Component
public class InmateManager {

    @Autowired
    private RestTemplate restTemplate;

    public InmatesResource fetchInmates() {
        String url = UriComponentsBuilder.fromHttpUrl("http://treatment-api/inmates").toUriString();
        return restTemplate.getForObject(url, InmatesResource.class);
    }

}
