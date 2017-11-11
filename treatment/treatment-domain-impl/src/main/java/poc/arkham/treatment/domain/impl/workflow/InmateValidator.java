package poc.arkham.treatment.domain.impl.workflow;

import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.impl.util.ValidationUtils;
import poc.arkham.treatment.domain.model.Inmate;
import poc.arkham.treatment.domain.model.Errors;
import org.springframework.stereotype.Component;
import poc.arkham.treatment.domain.model.InmateTransition;

@Component
public class InmateValidator {

    public void validate(Inmate inmate, InmateTransition transition) throws InvalidStateException {
        Errors errors = Errors.noError();
        ValidationUtils.checkNotEmpty(inmate.getLastname(), "lastname", errors);
        ValidationUtils.checkNotEmpty(inmate.getFirstname(), "firstname", errors);
        switch (transition) {
            case RELEASE:
                // extra validations
                break;
        }
        if (errors.hasErrors()) {
            throw new InvalidStateException(errors);
        }
    }

}
