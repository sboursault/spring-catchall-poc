package poc.arkham.research.api.controller;

import com.arkham.research.api.resource.MedicineResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineService;
import poc.arkham.research.api.mapper.MedicineMapper;

import static poc.arkham.common.web.util.RestPreconditions.*;

/**
 * <p>A simple rest controller to expose inmates.</p>
 */
@RestController
@RequestMapping("/medicines")
public class MedicineRestController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResource> create(@RequestBody MedicineResource input) {
        Medicine entity = MedicineMapper.INSTANCE.map(input);
        validateNull(entity.getId(), "id");
        Medicine persisted = medicineService.register(entity);
        MedicineResource response = MedicineMapper.INSTANCE.map(persisted);
        // TODO: add self link
        return ResponseEntity
                .ok()
                //.created(response.getUri())
                .body(response);
    }

}
