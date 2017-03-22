/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.employee.employeecontroller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.reln.Contact;
import np.com.drose.data.modules.employee.reln.JobDetail;
import np.com.drose.data.modules.employee.reln.Salary;
import np.com.drose.data.modules.employee.service.EmployeeEditService;
import np.com.drose.payroll.ui.admin.login.utility.FacesUtils;

/**
 *
 * @author bibekshakya
 */
@Named
@SessionScoped
public class EmployeeEditController implements Serializable {

    @Inject
    EmployeeEditService editServices;

    private Employee employee;
    private Contact contact;
    private JobDetail detail;
    private Salary salary;

    public Employee.Gender[] getGenders() {
        return Employee.Gender.values();
    }

    public Employee.MaritialStatus[] getMaritialStatuses() {
        return Employee.MaritialStatus.values();
    }

    public Employee.Position[] getPositions() {
        return Employee.Position.values();
    }

    public JobDetail.Department[] getDepartments() {
        return JobDetail.Department.values();
    }

    public JobDetail.EmployeeRole[] getEmployeeRoles() {
        return JobDetail.EmployeeRole.values();
    }

    public JobDetail.Shift[] getEmployeeShift() {
        return JobDetail.Shift.values();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public JobDetail getDetail() {
        return detail;
    }

    public void setDetail(JobDetail detail) {
        this.detail = detail;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @PostConstruct
    public void init() {

    }

    public String loadData(Employee employees) {
        try{
              this.setEmployee(employees);
        return "pretty:employee_edit";
        }catch(Exception e){
            FacesUtils.addErrorMessage("Employee record is not found");
            return null;
        }
      
    }

    public String updateEmployee() {
        try {
            if (employee.getEmployeeCode() == 0) {
                return null;
            }
            this.editServices.editEmployee(employee);
            return "pretty:list_employee";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("Sorry, unable to add Employee record, Please be Sure all the record in each field are correctly enter");
            return null;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
