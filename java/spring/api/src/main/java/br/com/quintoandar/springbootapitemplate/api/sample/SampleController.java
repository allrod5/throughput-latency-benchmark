package br.com.quintoandar.springbootapitemplate.api.sample;

import br.com.quintoandar.springbootapitemplate.core.exceptions.UnknownBusinessException;
import br.com.quintoandar.springbootapitemplate.core.sample.SampleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(path = "/")
@Api(value = "sample", description = "Sample Controller to test the Swagger")
public class SampleController {

    private final transient SampleService sampleService;

    @Autowired
    public SampleController(final SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GET
    @GetMapping
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return this.sampleService.getSample();
    }
}
