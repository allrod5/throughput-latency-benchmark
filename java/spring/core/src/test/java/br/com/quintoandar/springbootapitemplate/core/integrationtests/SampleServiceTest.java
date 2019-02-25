package br.com.quintoandar.springbootapitemplate.core.integrationtests;

import br.com.quintoandar.springbootapitemplate.core.CoreApplicationTest;
import br.com.quintoandar.springbootapitemplate.core.sample.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplicationTest.class})
public class SampleServiceTest {

    @Autowired
    private transient SampleService sampleService;

    @Test
    public void sample() {
        assertThat(this.sampleService.getSample(), hasSize(3));
    }

}
