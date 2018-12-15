package com.hsh.service;

import com.hsh.model.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("demo")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface UserService {

    @POST
    @Path("/getUserById/{userId}")
    UserDTO getUserById(@PathParam("userId") Integer userId);

    @POST
    @Path("/getUserByProvince/{province}")
    List<UserDTO> getUserByProvince(@PathParam("province") String province);

}
