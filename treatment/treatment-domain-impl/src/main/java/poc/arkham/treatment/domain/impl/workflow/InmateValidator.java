package poc.arkham.treatment.domain.impl.workflow;


import poc.arkham.treatment.domain.impl.util.ValidationUtils;
import poc.arkham.treatment.domain.model.Inmate;
import poc.arkham.treatment.domain.model.Errors;
import org.springframework.stereotype.Component;

@Component
public class InmateValidator {

    public boolean isValidForRegistration(Inmate inmate, Errors errors) {
        return checkBasicData(inmate, errors);
    }

    public boolean isValidForLockup(Inmate inmate, Errors errors) {
        return isValidForRegistration(inmate, errors);
    }

    public boolean isValidForRelease(Inmate inmate, Errors errors) {
        return isValidForRegistration(inmate, errors);
    }

    private boolean checkBasicData(Inmate inmate, Errors errors) {
        return ValidationUtils.checkNotEmpty(inmate.getLastname(), "lastname", errors)
                & ValidationUtils.checkNotEmpty(inmate.getFirstname(), "firstname", errors);
    }
}
