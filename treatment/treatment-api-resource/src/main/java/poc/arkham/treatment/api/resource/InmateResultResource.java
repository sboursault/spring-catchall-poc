package poc.arkham.treatment.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/*
 * Wrapper for an inmate list.
 * It adds an hypermedia link to the inmate collection.
 */
public class InmateResultResource extends ResourceSupport {

	private final List<InmateResource> results = new ArrayList<>();

	@JsonProperty("_embedded")
	public List<InmateResource> getResults() {
		return results;
	}

    public InmateResultResource() {
        // empty constructor required by com.fasterxml.jackson to deserialize json payload
    }

    public InmateResultResource(List<InmateResource> results) {
	    this.results.addAll(results);
    }

    // TODO move somewhere else
    @JsonIgnore
    public InmateResultResource withLink(Link link) {
        super.add(link);
        return this;
    }
}
