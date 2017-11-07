package poc.arkham.common.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import poc.arkham.common.web.resource.ErrorResource;


@ControllerAdvice
public class RestControllerAdvice {

    // TODO: catch throwable

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResource handleNotReadable(HttpMessageNotReadableException e) {
        // redefine the behaviors defined in DefaultHandlerExceptionResolver
        // to add the exception message in the response body
        return new ErrorResource(e.getMessage());
    }

}
