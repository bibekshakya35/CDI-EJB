/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.payroll.services.employee.EmployeeAttendance;
import np.com.drose.payroll.services.employee.impl.EmployeeAddServiceImpl;

/**
 *
 * @author bibekshakya
 */
@Path("secured")
@RequestScoped
public class EmployeeAddResources {
    
    @Inject
    EmployeeAddServiceImpl employeeAddServiceImpl;
    
    @Path("/employee/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(EmployeeAttendance employeeAttendance){
        employeeAddServiceImpl.insertEmployee(employeeAttendance);
        return Response.ok().status(200).entity("Saved").build();
    }
    
    
}
