/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.payroll.platform.PayrollException;

/**
 *
 * @author bibekshakya
 */
@Stateless
@LocalBean
public class EmployeeEditService {

    private static final Logger LOG = Logger.getLogger(EmployeeEditService.class.getName());
    
    
    
    @EJB
    EntityManagerWrapper managerWrapper;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editEmployee(Employee employee){
        try {
            if (employee.getEmployeeCode()==0) {
                throw new  PayrollException("Employee code is blank");
            }
            this.managerWrapper.merge(employee);
        } catch (Exception e) {
                      LOG.info("error in data while editing employee"+e.getMessage().toString());
        }
    }
        
}
