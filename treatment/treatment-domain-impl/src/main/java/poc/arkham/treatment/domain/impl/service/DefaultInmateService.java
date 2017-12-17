package poc.arkham.treatment.domain.impl.service;

import org.apache.commons.lang.math.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import poc.arkham.common.util.PartialResult;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.impl.repository.InmateRepository;
import poc.arkham.treatment.domain.impl.workflow.InmateValidator;
import poc.arkham.treatment.domain.model.Inmate;
import poc.arkham.treatment.domain.service.InmateService;

import static poc.arkham.treatment.domain.model.InmateTransition.REGISTRATION;

@Component
class DefaultInmateService implements InmateService {

    @Autowired
    private InmateValidator inmateValidator;

    @Autowired
    private InmateRepository inmateRepository;

    @Override
    public Inmate findById(String id) throws InmateNotFoundException {
        return inmateRepository.findById(id)
                .orElseThrow(() -> new InmateNotFoundException(id));
        // throwing an InmateNotFoundException is arguable.
        // We could return a java.util.Optional (much cheaper for the JVM).
        // On the other hand, on more complex method, an exception may simplify the logic.
    }

    @Override
    public PartialResult<Inmate> find(IntRange range) {
        return inmateRepository.find(range);
    }

    @Override
    public Inmate register(Inmate inmate) throws InvalidStateException {

        Assert.notNull(inmate, "[Assertion failed] - inmate is required; it must not be null");

        inmateValidator.validate(inmate, REGISTRATION);

        return inmateRepository.save(inmate);
    }
}
