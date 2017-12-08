package uk.gov.dropwizard.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/foo")
public class MyAppResource {

    @GET
    public Response foo() {
        return Response.ok().build();
    }
}
