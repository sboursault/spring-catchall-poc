package poc.arkham.inmate.domain.exception;

import org.springframework.util.Assert;
import poc.arkham.inmate.domain.model.Errors;

import java.util.stream.Collectors;

public class InvalidStateException extends Exception {

    Errors errors;

    public InvalidStateException(Errors errors) {
        super(errors.getAll().stream().map(Errors.Error::getMessage).collect(Collectors.joining(", ")));
        Assert.isTrue(errors.hasErrors(), "no error registered");
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
