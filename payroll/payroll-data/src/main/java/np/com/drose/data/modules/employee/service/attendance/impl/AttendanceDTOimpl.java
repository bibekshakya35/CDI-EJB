/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service.attendance.impl;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.reln.Attendance;
import np.com.drose.data.modules.employee.service.attendance.AttendanceDTO;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class AttendanceDTOimpl implements AttendanceDTO {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public Long getAbsentToday() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("appearance", Attendance.Appearance.Absent);
        return entityManagerWrapper.find(Employee.FIND_TODAY_ABSENT, parameters);
    }

    @Override
    public Long getTotalNumberOfEmployee() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employeeStatus", Employee.EmployeeStatus.Active);
        return (long) entityManagerWrapper.findAll(Employee.FIND_NO, parameters).size();
    }

}
