package poc.arkham.research.api.mapper;

import com.arkham.research.api.resource.MedicineResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.model.MedicineState;

@Mapper
public interface MedicineMapper {

    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    MedicineResource map(Medicine source);

    default Medicine map(MedicineResource source) {
        return Medicine.MedicineBuilder.newMedicine()
                .label(source.getLabel())
                .state(MedicineState.AUTHORIZED_SELLING)
                .build();
    }

}
