package poc.arkham.common.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/*
 * Wrapper for a result list.
 * It adds an hypermedia links to the results.
 */
public class ResultResource<T> extends ResourceSupport {

	private final List<T> results = new ArrayList<>();

	@JsonProperty("_embedded")
	public List<T> getResults() {
		return results;
	}

    public ResultResource() {
        // empty constructor required by com.fasterxml.jackson to deserialize json payload
    }

    public ResultResource(List<T> results) {
	    this.results.addAll(results);
    }

    @JsonIgnore
    public ResultResource withLink(Link link) {
        super.add(link);
        return this;
    }
}
