/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.user.services.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.modules.user.services.UserValidation;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.payroll.platform.PayrollException;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class UserValidator implements UserValidation{

    @Override
    public void validation(User user) {
        if (user.getUserName().equals("")) {
            throw new PayrollException("Illegal Authorization");
        }
    }
    
    
}
