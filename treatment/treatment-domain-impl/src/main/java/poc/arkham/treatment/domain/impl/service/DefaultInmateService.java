package poc.arkham.treatment.domain.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.impl.repository.InmateRepository;
import poc.arkham.treatment.domain.impl.workflow.InmateValidator;
import poc.arkham.treatment.domain.model.Inmate;
import poc.arkham.treatment.domain.service.InmateService;

import java.util.List;

import static poc.arkham.treatment.domain.model.InmateTransition.REGISTRATION;

@Component
class DefaultInmateService implements InmateService {

    @Autowired
    private InmateValidator inmateValidator;

    @Autowired
    private InmateRepository inmateRepository;

    public Inmate findById(String id) throws InmateNotFoundException {
        Inmate entity = inmateRepository.findOne(id);
        if (entity == null) {
            throw new InmateNotFoundException(id);
        }
        return entity;
    }

    public List<Inmate> findAll() {
        return inmateRepository.findAll();
    }

    public Inmate register(Inmate inmate) throws InvalidStateException {

        Assert.notNull(inmate, "[Assertion failed] - inmate is required; it must not be null");

        inmateValidator.validate(inmate, REGISTRATION);

        return inmateRepository.save(inmate);
    }
}
