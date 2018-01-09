package poc.arkham.treatment.api.assembler;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import poc.arkham.treatment.api.mapper.InmateMapper;
import poc.arkham.treatment.api.resource.InmateResource;
import poc.arkham.treatment.domain.model.Inmate;

import static poc.arkham.treatment.api.controller.Link.toInmate;
import static poc.arkham.treatment.api.controller.Link.toInmateCollection;
import static poc.arkham.treatment.api.controller.Link.toStart;

@Component
public class InmateResourceAssembler implements ResourceAssembler<Inmate, InmateResource> {

    @Override
    public InmateResource toResource(Inmate entity) {
        return InmateMapper.INSTANCE.map(entity)
                .withLink(toInmate(entity.getId()))
                .withLink(toInmateCollection())
                .withLink(toStart());
    }
}
