package poc.arkham.treatment.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.arkham.treatment.api.mapper.InmateMapper;
import poc.arkham.treatment.api.resource.InmateResource;
import poc.arkham.treatment.api.resource.InmatesResource;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.model.Inmate;
import poc.arkham.treatment.domain.service.InmateService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;
import static poc.arkham.common.web.util.RestPreconditions.validate;
import static poc.arkham.common.web.util.RestPreconditions.validateNotNull;
import static poc.arkham.common.web.util.RestPreconditions.validateNull;
import static poc.arkham.treatment.api.resource.InmatesResource.InmatesResourceBuilder.newInmatesResource;

/**
 * <p>A simple rest controller to expose inmates.</p>
 */
@RestController
@RequestMapping("/inmates")
public class InmateRestController {

    @Autowired
    private InmateService inmateService;

    @GetMapping
    public ResponseEntity find(Pageable pageable) {
        // if range is too big, return 400 with reason/error : "Requested range not allowed"
        Page<Inmate> results = inmateService.find(pageable);
        return ResponseEntity.status(results.getTotalPages() == 1 ? OK : PARTIAL_CONTENT)
                .header("Content-Range", results.getNumber() + "-" + (results.getNumber() + results.getSize()) + "/" + results.getSize())
                .header("Accept-Range", "inmate 25")
                .body(convertToResourceList(results));
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

    private InmatesResource convertToResourceList(Page<Inmate> sources) {
        List<InmateResource> resources = sources.getContent().stream()
                .map(InmateMapper.INSTANCE::map)
                .collect(Collectors.toList());
        resources.forEach(it -> it.add(Link.toInmate(it.getInmateId())));
        return newInmatesResource()
                .results(resources)
                .with(
                        Link.toInmateCollection(),
                        Link.toStart())
                .build();
    }

}
