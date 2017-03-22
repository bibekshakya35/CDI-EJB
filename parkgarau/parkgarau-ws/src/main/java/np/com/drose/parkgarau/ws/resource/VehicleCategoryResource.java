/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.ws.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.parkgarau.api.ParkGarauService;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
@Path("/park")
public class VehicleCategoryResource {
    @Inject ParkGarauService<np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory> vehParkGarauService;
    
    @GET
    @Path("/vehiclecategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<np.com.drose.parkgarau.ws.dto.VehicleCategory> vehicleCategorys = new ArrayList<>();
        vehParkGarauService.getList().stream().forEach((vp)->{
            vehicleCategorys.add(new np.com.drose.parkgarau.ws.dto.VehicleCategory(vp.getCode(), vp.getName(), vp.getDescription()));
        });
        return Response.ok().entity(vehicleCategorys).build();
    }
}
