package poc.arkham.treatment.api.controller;

import poc.arkham.treatment.api.resource.RootResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Rest controller to discover the asylum api.</p>
 */
@RestController
@RequestMapping("/")
public class RootRestController {

    @GetMapping
    public ResourceSupport start() {
        RootResource rootResource = new RootResource();
        rootResource.add(Link.toInmateCollection().withRel("inmates"));
        return rootResource;
    }

}
