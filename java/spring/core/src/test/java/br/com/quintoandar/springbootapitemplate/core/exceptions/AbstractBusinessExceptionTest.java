package br.com.quintoandar.springbootapitemplate.core.exceptions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AbstractBusinessExceptionTest {
    @Test
    public void unknownExceptionTest() {
        final AbstractBusinessException.Codes code = AbstractBusinessException.Codes.UNKNOWN_EXCEPTION;

        assertThat(code.getCode(), is(4000L));
        assertThat(code.getMessage(), is("An unknown exception was thrown."));
    }

    @Test
    public void internalServerErrorExceptionTest() {
        final AbstractBusinessException.Codes code = AbstractBusinessException.Codes.INTERNAL_SERVER_ERROR_EXCEPTION;

        assertThat(code.getCode(), is(5000L));
        assertThat(code.getMessage(), is("An internal server error was thrown."));
    }
}
