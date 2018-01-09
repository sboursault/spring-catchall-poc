package poc.arkham.treatment.api.controller;

import org.apache.commons.lang.math.IntRange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import poc.arkham.common.web.config.ApplicationProperties;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static poc.arkham.common.web.config.ApplicationContextProvider.getApplicationContext;

/**
 * Factory class to create inmate links.
 */
public class Link {

    private static final String REL_START = "start";
    private static final String REL_COLLECTION = "collection";

    // TODO add a templated link

    public static org.springframework.hateoas.Link toStart() {
        return newLinkTo(methodOn(RootRestController.class).start())
                .withRel(REL_START);
    }

    public static org.springframework.hateoas.Link toInmate(String id) {
        try {
            return newLinkTo(methodOn(InmateRestController.class).findById(id))
                    .withSelfRel();
        } catch (InmateNotFoundException e) {
            // can't happen: a dummy InmateRestController is used
            throw new RuntimeException(e);
        }
    }

    public static org.springframework.hateoas.Link toInmateCollection() {
        return toInmateCollection(Optional.empty());
    }

    public static org.springframework.hateoas.Link toInmateCollection(Optional<IntRange> range) {
        LinkedMultiValueMap parameters = new LinkedMultiValueMap();
        if (range.isPresent()) {
            parameters.add("range", range.get().getMinimumInteger() + "-" + range.get().getMaximumInteger());
        }
        return newLinkTo(methodOn(InmateRestController.class).find(null), parameters)
                .withRel(REL_COLLECTION);
    }

    private static org.springframework.hateoas.Link newLinkTo(Object method) {
        return newLinkTo(method, new LinkedMultiValueMap());
    }

    private static org.springframework.hateoas.Link newLinkTo(Object method, MultiValueMap<String, String> parameters) {
        final String uri = linkTo(method).toUri().getPath();
        String url = UriComponentsBuilder.fromHttpUrl(getBasePath())
                .path(uri)
                .queryParams(parameters)
                .toUriString();
        return new org.springframework.hateoas.Link(url);
    }

    private static String getBasePath() {
        return getApplicationContext().getBean(ApplicationProperties.class).getBaseUrl();
    }

}
