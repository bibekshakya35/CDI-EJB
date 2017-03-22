/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource.tryout;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import np.com.drose.payroll.services.tryout.CustomerManager;

/**
 * REST Web Service
 *
 * @author bibekshakya
 */
@Path("region")
@RequestScoped
public class RegionResource {
    
    @EJB
    CustomerManager customer;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegionResource
     */
    public RegionResource() {
    }

    /**
     * Retrieves representation of an instance of np.com.drose.payroll.services.resource.tryout.RegionResource
     * @param region
     * @return an instance of java.lang.String
     */
    @Path("{r}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public int getPathParam(@PathParam("r") String region){
        return customer.getCustomerByRegion(region);
    }
    @Path("regionBy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public int getXhml(@QueryParam("r") @DefaultValue("west") String region) {
      return  customer.getCustomerByRegion(region);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
      return "customer number"+customer.getCustomerCount();
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerByRegion(@FormParam("region") String region){
        return "customer "+customer.getCustomerByRegion(region);
    }

}
