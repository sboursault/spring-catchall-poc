package com.arkham.research.api.resource;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.net.URI;
import java.util.List;

/*
 * Resource representing a medicine.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label", "description"
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResource extends ResourceSupport {

	@ApiModelProperty(example = "XYZ 1", required = true, position = 0)
	private String label;

	@ApiModelProperty(example = "A text to describe what is inside, and what it does.", required = true, position = 1)
	private String description;

	@JsonIgnore
	public URI getUri() {
		Link link = getId();
		return link == null ? null : URI.create(link.getHref());
	}

	@ApiModelProperty(example = "[]", position = 2, hidden = true) // [] does nothing, test allow empty value? hidden ?
	// TODO try cqrs to simplify swagger doc
	@JsonProperty("links")
	public List<Link> getLinks() {
		return super.getLinks();
	}

	// TODO move somewhere else
	@JsonIgnore
	public MedicineResource withLink(Link link) {
		super.add(link);
		return this;
	}

}
