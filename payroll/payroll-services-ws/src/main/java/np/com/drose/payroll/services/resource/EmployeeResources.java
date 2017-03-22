/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.payroll.services.employee.EmployeeServices;
import np.com.drose.payroll.services.attendance.AttendanceServices;
import np.com.drose.payroll.services.employee.EmployeeAttendance;

/**
 *
 * @author bibekshakya
 */
@Stateless
@Path("secured")
public class EmployeeResources {
    
    @Inject
    EmployeeServices emplServices;
    
    @Inject
    AttendanceServices attendanceServices;
    
    @Path("/employee/attendance")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttendance(){
        final List<EmployeeAttendance> employeeList = emplServices.listEmployee();
        
        return Response.ok().entity(employeeList).build();
    }
    
    @Path("/employee/absent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAbsent(){
        return Response.ok().entity(attendanceServices.getTodayAbsent()).build();
    }
}
