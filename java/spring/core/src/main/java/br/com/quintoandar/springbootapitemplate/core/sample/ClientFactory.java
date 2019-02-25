package br.com.quintoandar.springbootapitemplate.core.sample;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory implements FactoryBean<Client> {
    @Override
    public Client getObject() {
        var client = new ResteasyClientBuilder().build();
        var target = client.target("http://localhost:2000");
        return target.proxy(Client.class);
    }

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }
}
