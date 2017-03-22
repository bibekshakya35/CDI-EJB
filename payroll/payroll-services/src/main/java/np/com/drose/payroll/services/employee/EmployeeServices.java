/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.service.EmployeeListServices;
import np.com.drose.payroll.services.employee.EmployeeAttendance;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class EmployeeServices {
    @Inject
    EmployeeListServices listEmployee;
    
    public List<EmployeeAttendance> listEmployee(){
        List<Employee> employeeList = listEmployee.listEmployee();
        EmployeeAttendance employeeAttendance = new EmployeeAttendance();
        List<EmployeeAttendance> emplist = new ArrayList<>();
        employeeList.stream().forEach((employee) -> {
            String employeeRestUrl="http://192.168.0.104:8080/payroll-services-ws/api/secured/image/"+employee.getEmployeePicture();
            boolean isenable;
            isenable = employee.getAttendance().getAppearance().equals(employee.getAttendance().getAppearance().Present);
            employeeAttendance.setId(employee.getEmployeeCode());
            employeeAttendance.setEmployeeName(employee.getEmployeeName());
            employeeAttendance.setFatherName(employee.getFatherName());
            employeeAttendance.setMotherName(employee.getMotherName());
            employeeAttendance.setEmployeeDob(getDate(employee.getEmployeeDob()));
            employeeAttendance.setEmployeeCitizenshipNumber(employee.getEmployeeCitizenshipNumber());
            employeeAttendance.setEmployeeNationality(employee.getEmployeeNationality());
            employeeAttendance.setEmployeeDateofCommencement(getDate(employee.getEmployeeDateOfCommencement()));
            employeeAttendance.setEmployeePic(employeeRestUrl);
            employeeAttendance.setMaritialStatus(employee.getMaritialStatus().toString());
            employeeAttendance.setSex(employee.getSex().toString());
            employeeAttendance.setEmployeeJobPosition(employee.getJobPosition().toString());
            employeeAttendance.setPhoneNumber(employee.getContact().getPhoneNumber());
            employeeAttendance.setMobileNumber(employee.getContact().getMobileNumber());
            employeeAttendance.setBasicSalary(employee.getSalary().getBasicSalary());
            
            employeeAttendance.setEmployeeClassification(employee.getDetail().getEmployeeClassification().toString());
            employeeAttendance.setEmployeeRole(employee.getDetail().getEmployeeRole().toString());
            employeeAttendance.setJobSpecification(employee.getDetail().getJobSpecification().toLowerCase());
            employeeAttendance.setShift(employee.getDetail().getShift().toString());
            employeeAttendance.setDepartment(employee.getDetail().getDepartment().toString());
            employeeAttendance.setEmployeeAttendanceStatus(isenable);
            emplist.add(employeeAttendance);
        });
        return emplist;
    }
    private String getDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MMM dd");
        return simpleDateFormat.format(date);
    }
}
