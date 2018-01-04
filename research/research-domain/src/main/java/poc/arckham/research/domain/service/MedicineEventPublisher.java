package poc.arckham.research.domain.service;

import poc.arckham.research.domain.model.Medicine;

public interface MedicineEventPublisher {

    void sendAuthorized(Medicine medicine);
}
