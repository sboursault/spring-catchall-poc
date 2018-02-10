package poc.arkham.research.api.assembler;

import com.arkham.research.api.resource.MedicineResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import poc.arckham.research.domain.model.Medicine;
import poc.arkham.research.api.mapper.MedicineMapper;

import static poc.arkham.research.api.controller.LinkProvider.linkToMedicine;

@Component
public class MedicineResourceAssembler implements ResourceAssembler<Medicine, MedicineResource> {

    @Override
    public MedicineResource toResource(Medicine entity) {
            return MedicineMapper.INSTANCE.map(entity)
                    .withLink(linkToMedicine(String.valueOf(entity.getId())));

    }

}
