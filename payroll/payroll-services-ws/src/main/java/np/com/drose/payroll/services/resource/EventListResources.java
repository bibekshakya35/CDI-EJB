/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
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
public class EventListResources {
    
    @Inject
    EventServices eventServices;
    
    
    @Path("/event/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventList(){
        List<Event> listEvents = new ArrayList<>();
        listEvents = this.eventServices.getAllEvent();
        return Response.ok().status(200).entity(listEvents).build();
    } 
}
