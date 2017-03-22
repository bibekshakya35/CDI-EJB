/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import np.com.drose.parkgarau.api.request.DeviceRegistrationRequest;

import np.com.drose.parkgarau.ws.resource.v1.ParkGarauWSAPI;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
@Path("/park")
public class DeviceResource {

    private static final Logger LOG = Logger.getLogger(DeviceResource.class.getName());

    
    @Inject
    ParkGarauWSAPI parkGarauWSAPI;
    
    @Inject
    Instance<ParkGarauWSAPI> parkGarauWSAPIs;

    private ParkGarauWSAPI getParkGarauWSAPI(int version) {
        for (ParkGarauWSAPI parkGarauWs : parkGarauWSAPIs) {
            if (parkGarauWs.getVersion() == version) {
                return parkGarauWs;
            }
        }
        return null;
    }
    
    @Path("/check")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public javax.ws.rs.core.Response require(){
        String name = "Hello world";
        return javax.ws.rs.core.Response.ok().status(200).entity(name).build();
    }

    @Path("/device/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("version") String version, DeviceRegistrationRequest deviceRegistrationRequest) {
        int ver = Integer.parseInt(version);
        parkGarauWSAPI = this.getParkGarauWSAPI(ver);
        LOG.log(Level.INFO, "here is value of device registration?>>>>>>>>>>>{0} : {1}", new Object[]{deviceRegistrationRequest.getCompanyCode(), deviceRegistrationRequest.getDeviceId()});
        return this.parkGarauWSAPI.registerDevice(deviceRegistrationRequest);
    }
    
    
}
