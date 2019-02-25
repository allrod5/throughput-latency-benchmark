package br.com.quintoandar.springbootapitemplate.api.interceptors;

import br.com.quintoandar.springbootapitemplate.api.response.BusinessExceptionResponseBuilder;
import br.com.quintoandar.springbootapitemplate.core.exceptions.AbstractBusinessException;
import br.com.quintoandar.springbootapitemplate.core.exceptions.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Controller
public class ExceptionHandlerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerConfiguration.class);

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ResponseEntity exceptionHandler(final RuntimeException exception) {
        if (exception instanceof AbstractBusinessException) {
            return new BusinessExceptionResponseBuilder((AbstractBusinessException) exception, BAD_REQUEST).build();
        }

        LOGGER.error(exception.getMessage(), exception);

        return new BusinessExceptionResponseBuilder(
                new InternalServerErrorException(), INTERNAL_SERVER_ERROR).build();
    }
}
