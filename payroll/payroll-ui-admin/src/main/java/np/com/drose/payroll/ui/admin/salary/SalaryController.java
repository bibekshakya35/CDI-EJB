/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.salary;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class SalaryController {
    
    @Inject 
    SalaryCalculationServiceImpl salaryCalculaterService;
    
    private Employee employee;
    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
    
    
    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void init(){
        
        employee = this.salaryCalculaterService.salaryCalculation(1);
    }
    public void rowSelect(SelectEvent event){
        FacesMessage msg  =new FacesMessage("Employee Selected",Integer.toString(((Employee)event.getObject()).getEmployeeCode()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//        
//    public void rowSelect(UnselectEvent event){
//        FacesMessage msg  =new FacesMessage("Employee Selected",Integer.toString(((Employee)event.getObject()).getEmployeeCode()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    
    
    
}
