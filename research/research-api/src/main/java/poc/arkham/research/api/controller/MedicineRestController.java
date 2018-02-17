package poc.arkham.research.api.controller;

import com.arkham.research.api.resource.MedicineResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineSearchService;
import poc.arckham.research.domain.service.MedicineService;
import poc.arkham.common.apiserver.util.ResponseEnityFactory;
import poc.arkham.common.web.exception.ResourceNotFoundException;
import poc.arkham.common.web.resource.ResultResource;
import poc.arkham.research.api.assembler.MedicineResourceAssembler;
import poc.arkham.research.api.mapper.MedicineMapper;

import java.net.URI;

import static poc.arkham.common.web.util.RestPreconditions.*;

/**
 * <p>A simple rest controller to expose medicines.</p>
 */
@RestController
@RequestMapping("/v1/medicines")
public class MedicineRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineRestController.class);
    private static final int MAX_PAGE_SIZE = 25;
    private static final String RESOURCE = "medicine";

    // if throwables don't print any stack trace, i should try this to keep the DefaultHandlerExceptionResolver
    // https://steveliles.github.io/configuring_global_exception_handling_in_spring_mvc.html  (Custom HandlerExceptionResolver)

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineSearchService medicineSearchService;

    @Autowired
    private MedicineResourceAssembler medicineResourceAssembler;

    @PostMapping
    public ResponseEntity<MedicineResource> create(@RequestBody MedicineResource input) {
        Medicine entity = MedicineMapper.INSTANCE.map(input);
        validateNull(entity.getId(), "id");
        Medicine persisted = medicineService.register(entity);
        MedicineResource response = medicineResourceAssembler.toResource(persisted);
        return ResponseEntity
                .created(URI.create(response.getId().getHref()))
                .body(response);
    }


    // curl -X GET --header 'Accept: application/json' 'http://localhost:8081/medicines?text=aaaa&page=0&size=5' | json_pp

    @GetMapping
    public ResponseEntity<ResultResource<MedicineResource>> search(
            @RequestParam(required = false, defaultValue = "") String text,
            @PageableDefault(size = 5, page = 0) Pageable pageable) {

        Page<MedicineResource> results = medicineSearchService.search(text, pageable)
                .map(medicineResourceAssembler::toResource);

        return ResponseEnityFactory
                .collection(pageable, results, RESOURCE, MAX_PAGE_SIZE);
    }

    @GetMapping("/{medicineId}")
    public MedicineResource fetch(@PathVariable("medicineId") String id)
            throws ResourceNotFoundException {

        Medicine entity = medicineService.fetch(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, RESOURCE));

        return medicineResourceAssembler.toResource(entity);
    }

}
