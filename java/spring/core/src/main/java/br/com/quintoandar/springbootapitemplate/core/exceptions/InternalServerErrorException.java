package br.com.quintoandar.springbootapitemplate.core.exceptions;

public class InternalServerErrorException extends AbstractBusinessException {

    public InternalServerErrorException() {
        super(Codes.INTERNAL_SERVER_ERROR_EXCEPTION);
    }
}
