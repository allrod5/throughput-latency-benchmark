package br.com.quintoandar.springbootapitemplate.core.exceptions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InternalServerErrorExceptionTest {
    @Test
    public void internalServerErrorExceptionTest() {
        final AbstractBusinessException exception = new InternalServerErrorException();

        assertThat(exception.getCode(), is(5000L));
        assertThat(exception.getMessage(), is("An internal server error was thrown."));
    }
}
