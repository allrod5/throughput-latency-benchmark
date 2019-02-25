package br.com.quintoandar.springbootapitemplate.api.integrationtests;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class SampleIntegrationTest {

    @Test
    public void fakeTest() {
        assertThat(true, is(equalTo(true)));
    }
}
