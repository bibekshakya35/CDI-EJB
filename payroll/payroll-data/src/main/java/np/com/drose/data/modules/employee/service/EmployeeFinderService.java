/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.employee.domain.Employee;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class EmployeeFinderService {
    @EJB
    private EntityManagerWrapper manager;
    
    public Employee getEmployeeByCode(int code){
       return manager.find(Employee.class, code);
    }
}
