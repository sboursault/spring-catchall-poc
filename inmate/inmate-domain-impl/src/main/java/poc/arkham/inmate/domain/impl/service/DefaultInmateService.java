package poc.arkham.inmate.domain.impl.service;

import poc.arkham.inmate.domain.exception.InmateNotFoundException;
import poc.arkham.inmate.domain.exception.InvalidStateException;
import poc.arkham.inmate.domain.model.Errors;
import poc.arkham.inmate.domain.model.Inmate;
import poc.arkham.inmate.domain.impl.repository.InmateRepository;
import poc.arkham.inmate.domain.service.InmateService;
import poc.arkham.inmate.domain.impl.workflow.InmateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DefaultInmateService implements InmateService {

    @Autowired
    private InmateValidator inmateValidator;

    @Autowired
    private InmateRepository inmateRepository;

    public Inmate findById(String id) throws InmateNotFoundException {
        Inmate inmate = inmateRepository.findOne(id);
        if (inmate == null) {
            throw new InmateNotFoundException(id);
        }
        return inmate;
    }

    public List<Inmate> findAll() {
        return inmateRepository.findAll();
    }

    public Inmate register(Inmate inmate) throws InvalidStateException {
        Errors errors = Errors.noError();
        inmateValidator.isValidForLockup(inmate, errors);
        if (errors.hasErrors())
            throw new InvalidStateException(errors);
        return inmateRepository.save(inmate);
    }
}
