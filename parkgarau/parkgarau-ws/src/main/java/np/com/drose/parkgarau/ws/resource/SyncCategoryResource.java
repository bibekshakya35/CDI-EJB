/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import np.com.drose.parkgarau.api.request.SyncCategoryRequest;
import np.com.drose.parkgarau.ws.resource.v1.ParkGarauWSAPI;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;
import np.com.drose.parkgarau.ws.security.Secured;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
@Path("/park")
public class SyncCategoryResource {

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

    @Secured
    @POST
    @Path("/sync/vehicle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response syncVehicle(@HeaderParam("version") String version,SyncCategoryRequest syncCategoryRequest) {
        int ver = Integer.parseInt(version);
        parkGarauWSAPI = this.getParkGarauWSAPI(ver);
        return parkGarauWSAPI.syncCategory(syncCategoryRequest);
    }
    
}
