package poc.arkham.treatment.api.assembler;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.math.IntRange;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import poc.arkham.common.util.PartialResult;
import poc.arkham.treatment.api.mapper.InmateMapper;
import poc.arkham.treatment.api.resource.InmateResource;
import poc.arkham.treatment.api.resource.InmateResultResource;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static poc.arkham.treatment.api.controller.Link.toInmate;
import static poc.arkham.treatment.api.controller.Link.toInmateCollection;
import static poc.arkham.treatment.api.controller.Link.toStart;

@Component
public class InmateResultResourceAssembler implements ResourceAssembler<PartialResult<Inmate>, InmateResultResource> {

    @Override
    public InmateResultResource toResource(PartialResult<Inmate> result) {
        List<InmateResource> resources = result.getContent().stream()
                .map(InmateMapper.INSTANCE::map)
                .collect(Collectors.toList());
        resources
                .forEach(inmate -> inmate.withLink(toInmate(inmate.getInmateId())));
        final InmateResultResource resource = new InmateResultResource(resources)
                .withLink(toStart());

        Map<String, Optional<IntRange>> rangePerRelation = ImmutableMap.of(
                Link.REL_FIRST, result.getFirstRange(),
                Link.REL_PREVIOUS, result.getPreviousRange(),
                Link.REL_NEXT, result.getNextRange(),
                Link.REL_LAST, result.getLastRange());

        rangePerRelation.forEach((rel, range) -> {
            if (range.isPresent()) {
                resource.withLink(toInmateCollection(range).withRel(rel));
            }
        });
        resource.withLink(toInmateCollection());
        return resource;

    }
}
