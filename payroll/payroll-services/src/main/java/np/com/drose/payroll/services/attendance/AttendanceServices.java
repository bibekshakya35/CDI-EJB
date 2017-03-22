/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.attendance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.data.modules.employee.service.attendance.AttendanceDTO;

/**
 *
 * @author bibekshakya
 */
@Stateless
@LocalBean
public class AttendanceServices {
    @Inject
    AttendanceDTO attendanceDTO;
    
    public Long getTodayAbsent(){
        return attendanceDTO.getAbsentToday();
    }
    
    public Long getTotalNumberEmployee(){
        return attendanceDTO.getTotalNumberOfEmployee();
    }
           
}
