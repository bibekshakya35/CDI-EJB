/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.data.modules.employee.domain.event.Event;
import np.com.drose.payroll.services.event.EventServices;

/**
 *
 * @author bibekshakya
 */
@Path("secured")
@Stateless
public class EventAddResurces {

    @Inject
    EventServices eventServices;
    

    @POST
    @Path("/event/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(Event event) {
        this.eventServices.addEvent(event);
        return Response.ok().status(200).entity(event).build();
    }
}
