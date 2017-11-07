package poc.arkham.treatment.api.controller;

import poc.arkham.common.web.resource.ErrorResource;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import poc.arkham.treatment.domain.model.Errors;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class InmateControllerAdvice {

    // TODO: catch throwable

    @ExceptionHandler({InmateNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorResource handleNotFound(Exception e) {
        return new ErrorResource(e.getMessage());
    }

    @ExceptionHandler({InvalidStateException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody
    ErrorResource handleUnprocessable(InvalidStateException e) {
        return new ErrorResource(e.getErrors().getAll().stream()
                .map(Errors.Error::getMessage)
                .collect(toList()));
    }
}
