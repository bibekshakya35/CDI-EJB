/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.payroll.services.salary.impl.SalaryCalculationServiceImpl;

/**
 *
 * @author bibekshakya
 */
@Path("secured")
@RequestScoped
public class SalaryListResources {
    @Inject
    SalaryCalculationServiceImpl salaryCalculationServiceImpl;
    
    @GET
    @Path("/salary/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalaryList(){
        Map<String,String> salaryMap = salaryCalculationServiceImpl.getSalaryListRest();
        return Response.ok().status(200).entity(salaryMap).build();
    }
    
}
