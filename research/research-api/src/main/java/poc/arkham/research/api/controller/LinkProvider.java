package poc.arkham.research.api.controller;

import org.springframework.hateoas.Link;
import poc.arkham.common.web.exception.ResourceNotFoundException;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Factory class to create links.
 */
public class LinkProvider {

    public static Link linkToMedicine(String id) {
        try {
            return linkTo(methodOn(MedicineRestController.class).fetch(id)).withSelfRel();
        } catch (ResourceNotFoundException e) {
            // can't happen: a dummy MedicineRestController is used
            throw new RuntimeException(e);
        }
    }
}