package poc.arkham.inmate.api.controller;

import poc.arkham.inmate.api.mapper.InmateMapper;
import poc.arkham.inmate.api.resource.InmateResource;
import poc.arkham.inmate.api.resource.InmatesResource;
import poc.arkham.inmate.domain.exception.InmateNotFoundException;
import poc.arkham.inmate.domain.exception.InvalidStateException;
import poc.arkham.inmate.domain.model.Inmate;
import poc.arkham.inmate.domain.service.InmateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static poc.arkham.common.util.RestPreconditions.validate;
import static poc.arkham.common.util.RestPreconditions.validateNotNull;
import static poc.arkham.common.util.RestPreconditions.validateNull;

/**
 * <p>A simple rest controller to expose inmates.</p>
 */
@RestController
@RequestMapping("/inmates")
@Api(description = "inmates API")
public class InmateRestController {

    @Autowired
    private InmateService inmateService;

    @GetMapping
    public InmatesResource findAll(/*Pageable pageable*/) {
        return convertToResourceList(inmateService.findAll(/*pageable*/));
    }

    @GetMapping(path = "/{id}")
    public InmateResource findById(@PathVariable("id") String id) throws InmateNotFoundException {
        return convertToResource(inmateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<InmateResource> create(@RequestBody InmateResource input) throws InvalidStateException {
        Inmate entity = convertToModel(input);
        validateNull(entity.getId(), "id");
        Inmate persisted = inmateService.register(entity);
        InmateResource response = convertToResource(persisted);
        return ResponseEntity
                .created(response.getUri())
                .body(response);
    }

    @PutMapping("/{id}")
    public InmateResource update(@PathVariable("id") String id, @RequestBody InmateResource input) throws InvalidStateException, InmateNotFoundException {
        //FIXME: a creation date would be erased.
        Inmate entity = convertToModel(input);
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(id);
        } else {
            validate(Objects.equals(id, entity.getId()), "inconsistant ids between the url and the payload");
        }
        validateNotNull(entity.getId(), "id");
        validateInmateExists(id); // TODO: could be in the service
        Inmate persisted = inmateService.register(entity);
        return convertToResource(persisted);
    }

    // helpers

    private void validateInmateExists(String id) throws InmateNotFoundException {
        inmateService.findById(id);
    }

    private InmateResource convertToResource(Inmate source) {
        InmateResource target = InmateMapper.INSTANCE.map(source);
        target.add(Link.toInmate(source.getId()));
        target.add(Link.toInmateCollection());
        target.add(Link.toStart());
        return target;
    }

    private Inmate convertToModel(InmateResource source) {
        return InmateMapper.INSTANCE.map(source);
    }

    private InmatesResource convertToResourceList(List<Inmate> sources) {
        List<InmateResource> resources = sources.stream()
                .map(InmateMapper.INSTANCE::map)
                .collect(Collectors.toList());
        resources.forEach(it -> it.add(Link.toInmate(it.getInmateId())));
        InmatesResource target = new InmatesResource(resources);
        target.add(Link.toInmateCollection());
        target.add(Link.toStart());
        return target;
    }

}
