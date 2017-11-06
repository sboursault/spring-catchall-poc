package poc.arkham.inmate.api.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * Resource representing an aka.
 */
@Data
public class AkaResource {

    @ApiModelProperty(example = "Scarecrow")
    private String name;
}
