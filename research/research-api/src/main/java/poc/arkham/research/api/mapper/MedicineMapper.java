package poc.arkham.research.api.mapper;

import com.arkham.research.api.resource.MedicineResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.model.MedicineState;

import static poc.arckham.research.domain.model.Medicine.newMedicine;

@Mapper
public interface MedicineMapper {

    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    MedicineResource map(Medicine source);

    default Medicine map(MedicineResource source) {
        return newMedicine()
                .label(source.getLabel())
                .description(source.getDescription())
                .state(MedicineState.AUTHORIZED_SELLING)
                .build();
    }

}
