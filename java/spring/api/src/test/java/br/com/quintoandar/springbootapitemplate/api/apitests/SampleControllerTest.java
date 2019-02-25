package br.com.quintoandar.springbootapitemplate.api.apitests;

import br.com.quintoandar.springbootapitemplate.api.sample.SampleController;
import br.com.quintoandar.springbootapitemplate.core.sample.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
public class SampleControllerTest {

    @Autowired
    private transient MockMvc mvc;

    @MockBean
    private transient SampleService sampleService;

    @Test
    public void allUsersTest() throws Exception {
        final List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Marie");
        list.add("Richard");

        given(sampleService.getSample()).willReturn(list);

        mvc.perform(get("/sample/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void exceptionTest() throws Exception {
        mvc.perform(get("/sample/exception")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{ code: 4000, message: 'An unknown exception was thrown.' }"));
    }

    @Test
    public void serverErrorTest() throws Exception {
        mvc.perform(get("/sample/server-error")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{ code: 5000, message: 'An internal server error was thrown.' }"));
    }

}
