package br.com.quintoandar.springbootapitemplate.api.response;

import br.com.quintoandar.springbootapitemplate.core.exceptions.AbstractBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BusinessExceptionResponseBuilder {
    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    private final transient AbstractBusinessException exception;
    private final transient HttpStatus status;

    public BusinessExceptionResponseBuilder(final AbstractBusinessException exception, final HttpStatus status) {
        Objects.requireNonNull(exception);
        Objects.requireNonNull(status);
        this.exception = exception;
        this.status = status;
    }

    public ResponseEntity build() {
        final Map<String, Object> responseMap = new ConcurrentHashMap<>();
        responseMap.put(CODE, this.exception.getCode());
        responseMap.put(MESSAGE, this.exception.getMessage());
        return new ResponseEntity<>(responseMap, this.status);
    }
}
