/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.testresource.test;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;

/**
 * Jersey REST client generated for REST resource:DeviceResource
 * [/parkgarau/device/register]<br>
 * USAGE:
 * <pre>
 *        UserService client = new UserService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bibek
 */
@Path("/user")
public class UserService {

    @POST
    @Path("/add")
    public javax.ws.rs.core.Response addUser(@FormParam("name")String name, @FormParam("age")String age){
        return javax.ws.rs.core.Response.status(200).entity("add user is called "+name+" age "+age).build();
    }
  
}
