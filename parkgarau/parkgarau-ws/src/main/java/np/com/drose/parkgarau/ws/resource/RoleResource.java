/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.utils.JSONUtils;
import np.com.drose.parkgarau.ws.dto.RoleDTO;

/**
 *
 * @author Bibek Shakya
 */
@Named
@RequestScoped
@Path("/park")
public class RoleResource {
    @Inject ParkGarauService<Role> roleGarauService;
    private static final Logger LOG = Logger.getLogger(RoleResource.class.getName());
    
    
    @GET
    @Path("/role")
    @Produces(MediaType.APPLICATION_JSON)
    public Response roleList(){
        List<RoleDTO> roleDTOs = new ArrayList<>();
        roleGarauService.getList().stream().forEach((role)->{
            roleDTOs.add(new RoleDTO(role.getCode(), "Sys "+role.getCode()));
        });
        LOG.info(JSONUtils.objectToJson(roleDTOs));
        return Response.ok().entity(roleDTOs).build();
    }
}
