package poc.arkham.common.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import poc.arkham.common.web.exception.ResourceNotFoundException;
import poc.arkham.common.web.resource.ErrorResource;


@ControllerAdvice
public class RestControllerAdvice {

    /**
     * <p>This handler changes the behaviours defined in {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver}
     * for {@link MethodArgumentTypeMismatchException}, so that an explicit message is returned in the response.</p>
     *
     * <p>{@link MethodArgumentTypeMismatchException} is thrown when an input could not be instantiated (e.g. RangeQueryParameter with invalid value)</p>
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResource handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return new ErrorResource(e.getRootCause().getMessage());
    }

    /**
     * <p>This handler changes the behaviours defined in {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver}
     * for {@link HttpMessageNotReadableException}, so that an explicit message is returned in the response.</p>
     *
     * <p>{@link HttpMessageNotReadableException} is thrown when the payload deserialization failed (e.g. invalid date pattern on entity)</p>
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResource handleNotReadable(HttpMessageNotReadableException e) {
        Throwable cause = e.getRootCause() != null ? e.getRootCause() : e;
        return new ErrorResource(cause.getMessage());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResource handleNotFound(ResourceNotFoundException e) {
        return new ErrorResource(e.getMessage());
    }

}
