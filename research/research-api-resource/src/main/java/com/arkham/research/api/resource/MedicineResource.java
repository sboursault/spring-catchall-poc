package com.arkham.research.api.resource;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.net.URI;

/*
 * Resource representing a medicine.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label"
})
@Data
public class MedicineResource extends ResourceSupport {

	@ApiModelProperty(example = "XYZ 1", required = true, position = 0)
	private String label;

	@JsonIgnore
	public URI getUri() {
		Link link = getId();
		return link == null ? null : URI.create(link.getHref());
	}

}
