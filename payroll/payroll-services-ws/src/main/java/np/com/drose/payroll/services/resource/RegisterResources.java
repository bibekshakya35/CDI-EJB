/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.payroll.services.user.UserAddServiceImpl;

/**
 *
 * @author bibekshakya
 */
@Path("secured")
@RequestScoped
public class RegisterResources {

    @Context
    UriInfo uriInfo;

    @Inject
    UserAddServiceImpl userAddServiceImpl;

    User user;

    @GET
    @Path("/register")
    public Response getRegister(
            @QueryParam("fullname") String fullName,
            @QueryParam("emailid") String emailId,
            @QueryParam("username") String username,
            @QueryParam("password") String password
    ) {
        user = new User();
        user.setFullname(fullName);
        user.setEmailAddress(emailId);
        user.setUserName(username);
        user.setPassword(password);
        //user.setRoles(User.UserROLE.USER);
        this.userAddServiceImpl.addUser(user);
        return Response.status(200).entity("Successfully Add").build();

    }
}
