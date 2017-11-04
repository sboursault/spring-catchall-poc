package poc.arkham.inmate.api.controller.resource;

import poc.arkham.inmate.api.controller.Link;
import org.springframework.hateoas.ResourceSupport;

/*
 * Holds hypermedia links for the root resource.
 */
public class RootResource extends ResourceSupport {

	public RootResource() {
		add(Link.toInmateCollection().withRel("inmates"));
	}

}
