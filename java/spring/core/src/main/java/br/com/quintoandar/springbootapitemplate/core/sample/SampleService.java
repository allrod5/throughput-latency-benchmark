package br.com.quintoandar.springbootapitemplate.core.sample;

import org.jboss.resteasy.client.jaxrs.internal.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Service
public class SampleService {

    public String getSample() {
        var futures = new ArrayList<Future<Response>>();
        var client = ClientBuilder.newClient();
        for (int i = 0; i < 100; i++) {
            futures.add(client.target("http://localhost:2000").request().async().get());
        }
        return String.join(",", futures.stream().map(future -> {
            try {
                ClientResponse a = (ClientResponse) future.get();
                return a.readEntity(String.class);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "";
        }).toArray(String[]::new));
    }

}
