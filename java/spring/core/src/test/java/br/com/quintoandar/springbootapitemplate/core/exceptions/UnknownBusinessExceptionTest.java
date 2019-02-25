package br.com.quintoandar.springbootapitemplate.core.exceptions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UnknownBusinessExceptionTest {
    @Test
    public void unknownBusinessExceptionTest() {
        final AbstractBusinessException exception = new UnknownBusinessException();

        assertThat(exception.getCode(), is(4000L));
        assertThat(exception.getMessage(), is("An unknown exception was thrown."));
    }
}
