package com.appsdeveloperblog.keycloak;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersApiService {

	@GET
	@Path("/{username}")
	User getUserDetails(@PathParam("username") String username);

    @PUT
    @Path("/{username}")
    User putUserDetails(@PathParam("username") String username);

    @GET
    List<User> getUsers();


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/verify-password")
    VerifyPasswordResponse verifyUserPassword(@PathParam("username") String username, 
            String password);
	
}
