/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.employee.domain.Employee;

/**
 *
 * @author bibekshakya
 */
@Stateless
@LocalBean
public class EmployeeListServices {
    
    @EJB
    EntityManagerWrapper manager;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Employee> listEmployee(){
        return this.manager.findAll(Employee.FIND_ALL);
    }
    
}
