package poc.arkham.treatment.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/*
 * Wrapper for an inmate list.
 * It adds an hypermedia link to the inmate collection.
 */
public class InmatesResource extends ResourceSupport {

	private List<InmateResource> results;

	@JsonProperty("_embedded")
	public List<InmateResource> getResults() {
		return results;
	}

	public void setResults(List<InmateResource> results) {
		this.results = results;
	}

	public static class InmatesResourceBuilder {

        private InmatesResource inmatesResource;

        private InmatesResourceBuilder()
        {
            inmatesResource = new InmatesResource();
        }

        public InmatesResourceBuilder results(List<InmateResource> results) {
            inmatesResource.results = results;
            return this;
        }

        public InmatesResourceBuilder with(Link... links) {
            inmatesResource.add(links);
            return this;
        }

        public static InmatesResourceBuilder newInmatesResource() {
            return new InmatesResourceBuilder();
        }


        public InmatesResource build()
        {
            return inmatesResource;
        }
    }
}
