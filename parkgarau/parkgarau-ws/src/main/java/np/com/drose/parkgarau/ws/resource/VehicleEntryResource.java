/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import np.com.drose.parkgarau.api.request.VehicleInRequest;
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
public class VehicleEntryResource {

    private static final Logger LOG = Logger.getLogger(VehicleEntryResource.class.getName());

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
    @Path("/vehiclein")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vehicleIn(@HeaderParam("version") String version, VehicleInRequest vehicleInRequest) {
        int ver = Integer.parseInt(version);
        parkGarauWSAPI = this.getParkGarauWSAPI(ver);
        LOG.info("vehicle in success within version");
        return parkGarauWSAPI.vehicleIn(vehicleInRequest);
    }
}
