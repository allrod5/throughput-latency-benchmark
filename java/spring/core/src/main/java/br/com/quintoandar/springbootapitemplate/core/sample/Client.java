package br.com.quintoandar.springbootapitemplate.core.sample;

import java.util.concurrent.Future;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface Client {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    Future<String> get();
}
