/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.employee.employeecontroller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.service.EmployeeListServices;
import np.com.drose.payroll.ui.admin.login.utility.FacesUtils;

/**
 *
 * @author bibekshakya
 */
@Named
@ViewScoped
public class EmployeeListController implements Serializable{
    
    @Inject
    private EmployeeListServices listServices;
    
    private List<Employee> employee;
    
    @PostConstruct
    public void init(){
        employee = this.listServices.listEmployee();
        if (employee.isEmpty()) {
            FacesUtils.addMessage(FacesMessage.SEVERITY_INFO, "No employee Record has been found");
        }
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    
    
    
}
