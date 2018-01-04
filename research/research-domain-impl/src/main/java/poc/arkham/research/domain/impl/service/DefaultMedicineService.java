package poc.arkham.research.domain.impl.service;

import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineEventPublisher;
import poc.arckham.research.domain.service.MedicineService;

import static poc.arckham.research.domain.model.MedicineState.AUTHORIZED_SELLING;

public class DefaultMedicineService implements MedicineService {

    private MedicineEventPublisher medicineEventPublisher;

    public DefaultMedicineService(MedicineEventPublisher medicineEventPublisher) {
        this.medicineEventPublisher = medicineEventPublisher;
    }

    @Override
    public Medicine register(Medicine entity) {

        // save new medicine

        if (entity.getState() == AUTHORIZED_SELLING) {
            medicineEventPublisher.sendAuthorized(entity);
        }

        return entity;

    }

}
