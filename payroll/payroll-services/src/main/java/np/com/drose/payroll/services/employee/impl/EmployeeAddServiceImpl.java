/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.employee.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.reln.Attendance;
import np.com.drose.data.modules.employee.reln.Contact;
import np.com.drose.data.modules.employee.reln.JobDetail;
import np.com.drose.data.modules.employee.reln.Salary;
import np.com.drose.data.modules.employee.service.EmployeeAddService;
import np.com.drose.payroll.services.employee.EmployeeAttendance;
import np.com.drose.payroll.services.utills.DateFormatter;

/**
 *
 * @author bibekshakya
 */
@LocalBean
@Stateless
public class EmployeeAddServiceImpl implements np.com.drose.payroll.services.employee.EmployeeAddService {

    @Inject
    EmployeeAddService employeeAddService;
    
    @Override
    public void insertEmployee(EmployeeAttendance employeeAttendance) {
        Employee employee = new Employee();
        Contact contact = new Contact();
        JobDetail jobDetail = new JobDetail();
        Salary salary = new Salary();
        Attendance attendance = new Attendance();
        
        employee.setEmployeeName(employeeAttendance.getEmployeeName());
        employee.setFatherName(employeeAttendance.getFatherName());
        employee.setMotherName(employeeAttendance.getMotherName());
        employee.setEmployeeDob(DateFormatter.getDate(employeeAttendance.getEmployeeDob()));
        employee.setEmployeeCitizenshipNumber(employeeAttendance.getEmployeeCitizenshipNumber());
        employee.setEmployeeNationality(employeeAttendance.getEmployeeNationality());
        employee.setEmployeePicture(employeeAttendance.getEmployeePic());
        employee.setEmployeeDateOfCommencement(DateFormatter.getDate(employeeAttendance.getEmployeeDateofCommencement()));
        employee.setMaritialStatus(Employee.MaritialStatus.valueOf(employeeAttendance.getMaritialStatus()));
        employee.setSex(Employee.Gender.valueOf(employeeAttendance.getSex()));
        employee.setJobPosition(Employee.Position.valueOf(employeeAttendance.getEmployeeJobPosition()));
        
        contact.setMobileNumber(employeeAttendance.getMobileNumber());
        contact.setPhoneNumber(employeeAttendance.getPhoneNumber());
        
        salary.setBasicSalary(employeeAttendance.getBasicSalary());
        
        jobDetail.setDepartment(JobDetail.Department.valueOf(employeeAttendance.getDepartment()));
        jobDetail.setEmployeeClassification(JobDetail.EmployeeClassification.valueOf(employeeAttendance.getEmployeeClassification()));
        jobDetail.setShift(JobDetail.Shift.valueOf(employeeAttendance.getShift()));
        jobDetail.setJobSpecification(employeeAttendance.getJobSpecification());
        jobDetail.setEmployeeRole(JobDetail.EmployeeRole.valueOf(employeeAttendance.getEmployeeRole()));
        
        contact.setEmployee(employee);
        salary.setEmployee(employee);
        jobDetail.setEmployee(employee);
        attendance.setEmployee(employee);
        
        employee.setContact(contact);
        employee.setDetail(jobDetail);
        employee.setAttendance(attendance);
        employee.setSalary(salary);
        
        employeeAddService.addEmployee(employee);
        
    }
    
}
