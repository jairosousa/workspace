package org.jnsdev;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("quarkus")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloquarkus() {
        return "Hello quarkus de novo";
    }
}