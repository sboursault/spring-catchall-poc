package poc.arkham.frontoffice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import poc.arkham.treatment.api.resource.InmatesResource;

@Component
public class InmateRepository {

    @Autowired
    private RestTemplate restTemplate;

    public InmatesResource findAll() {
        return restTemplate.getForObject("http://treatment-api/inmates", InmatesResource.class);
    }

}
