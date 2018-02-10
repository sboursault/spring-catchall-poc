package poc.arckham.research.domain.service;

import poc.arckham.research.domain.model.Medicine;

import java.util.Optional;

public interface MedicineService {

    Medicine register(Medicine entity);

    Optional<Medicine> fetch(String id);
}
