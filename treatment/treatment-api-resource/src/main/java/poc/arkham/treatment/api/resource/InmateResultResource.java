package poc.arkham.treatment.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/*
 * Wrapper for an inmate list.
 * It adds an hypermedia link to the inmate collection.
 */
public class InmateResultResource extends ResourceSupport {

	private List<InmateResource> results;

	@JsonProperty("_embedded")
	public List<InmateResource> getResults() {
		return results;
	}

    public InmateResultResource(List<InmateResource> results) {
        this.results = results;
    }

    // TODO move somewhere else
    @JsonIgnore
    public InmateResultResource withLink(Link link) {
        super.add(link);
        return this;
    }
}
