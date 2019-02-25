package br.com.quintoandar.springbootapitemplate.core.exceptions;

public class UnknownBusinessException extends AbstractBusinessException {

    public UnknownBusinessException() {
        super(Codes.UNKNOWN_EXCEPTION);
    }
}
