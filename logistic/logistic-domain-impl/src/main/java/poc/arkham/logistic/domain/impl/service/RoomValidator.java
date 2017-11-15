package poc.arkham.logistic.domain.impl.service;

import org.springframework.stereotype.Component;
import poc.arkham.common.util.Errors;
import poc.arkham.common.util.ValidationUtils;
import poc.arkham.logistic.domain.exception.InvalidStateException;
import poc.arkham.logistic.domain.model.Room;

@Component
public class RoomValidator {

    public void validate(Room room) throws InvalidStateException {
        Errors errors = Errors.noError();
        ValidationUtils.checkNotEmpty(room.getName(), "name", errors);
        if (errors.hasErrors()) {
            throw new InvalidStateException(errors);
        }
    }

}
