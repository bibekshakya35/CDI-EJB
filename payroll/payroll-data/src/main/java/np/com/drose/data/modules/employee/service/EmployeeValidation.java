/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.payroll.platform.PayrollException;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class EmployeeValidation implements EmployeeValidator{

    @Override
    public void validation(Employee employee) {
        if (employee.getEmployeeCode()==0) {
            throw new PayrollException("Employee code is null");
        }
    }
    
    
}
