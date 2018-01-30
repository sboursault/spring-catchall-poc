package poc.arkham.frontoffice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import poc.arkham.treatment.api.resource.InmateResultResource;

@Component
public class InmateRepository {

    @Autowired
    private RestTemplate restTemplate;

    public InmateResultResource findAll() {
        return restTemplate.getForObject("http://treatment-api/v1/inmates", InmateResultResource.class);
    }

}
