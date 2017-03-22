/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.attendance;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.payroll.services.attendance.AttendanceServices;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class AttendanceController {
    
    @Inject
    AttendanceServices attendanceServices;
    
    private Long absentNumber;
    private long totalNoOfEmployee=0;
    
    
    
    @PostConstruct
    public void init(){
        absentNumber = this.attendanceServices.getTodayAbsent();
        totalNoOfEmployee=this.attendanceServices.getTotalNumberEmployee();
    }

    public long getTotalNoOfEmployee() {
        return totalNoOfEmployee;
    }

    public void setTotalNoOfEmployee(long totalNoOfEmployee) {
        this.totalNoOfEmployee = totalNoOfEmployee;
    }

    public Long getAbsentNumber() {
        return absentNumber;
    }

    public void setAbsentNumber(Long absentNumber) {
        this.absentNumber = absentNumber;
    }

    
    
    

    
}
