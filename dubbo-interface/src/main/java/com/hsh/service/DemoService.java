package com.hsh.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Map;

@Path("demo")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface DemoService {

    @POST
    @Path("/sayHello/{uername}")
    Map<String,Object> sayHello(@PathParam("uername") String name);
}
