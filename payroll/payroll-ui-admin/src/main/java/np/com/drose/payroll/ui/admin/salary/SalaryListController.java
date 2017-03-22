/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.salary;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.payroll.services.salary.impl.SalaryCalculationServiceImpl;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class SalaryListController {
    @Inject
    SalaryCalculationServiceImpl salaryCalculationServiceImpl;
    
    private Employee employee;
    
    List<Employee> employeeList;
    
    
    @PostConstruct
    public void init(){
        employee = new Employee();
        employeeList = salaryCalculationServiceImpl.getSalaryList();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        
        this.employeeList = employeeList;
    }

    
    
    public void onSelectedRow(SelectEvent event){
        FacesMessage msg = new FacesMessage("Employee Selected",Integer.toString(((Employee)event.getObject()).getEmployeeCode()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
