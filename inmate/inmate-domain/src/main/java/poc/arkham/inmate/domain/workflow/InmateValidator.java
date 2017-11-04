package poc.arkham.inmate.domain.workflow;


import poc.arkham.inmate.domain.util.ValidationUtils;
import poc.arkham.inmate.domain.model.Inmate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

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
