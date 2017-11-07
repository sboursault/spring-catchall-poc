package poc.arkham.treatment.api.resource;

import poc.arkham.treatment.api.controller.Link;
import org.springframework.hateoas.ResourceSupport;

/*
 * Holds hypermedia links for the root resource.
 */
public class RootResource extends ResourceSupport {

	public RootResource() {
		add(Link.toInmateCollection().withRel("inmates"));
	}

}
