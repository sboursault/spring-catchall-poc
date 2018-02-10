package poc.arkham.research.domain.impl.service;

import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineEventPublisher;
import poc.arckham.research.domain.service.MedicineService;
import poc.arkham.research.domain.impl.repository.MedicineRepository;

import java.util.Optional;

import static poc.arckham.research.domain.model.MedicineState.AUTHORIZED_SELLING;

public class DefaultMedicineService implements MedicineService {

    private MedicineRepository repository;
    private MedicineEventPublisher medicineEventPublisher;

    public DefaultMedicineService(MedicineRepository repository, MedicineEventPublisher medicineEventPublisher) {
        this.repository = repository;
        this.medicineEventPublisher = medicineEventPublisher;
    }

    @Override
    public Medicine register(Medicine entity) {

        repository.save(entity);

        if (entity.getState() == AUTHORIZED_SELLING) {
            medicineEventPublisher.sendAuthorized(entity);
        }

        return entity;

    }

    @Override
    public Optional<Medicine> fetch(String id) {
        return Optional.ofNullable(repository.findOne(id));
    }
}
