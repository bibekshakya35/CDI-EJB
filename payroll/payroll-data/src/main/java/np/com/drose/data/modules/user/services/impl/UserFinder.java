/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.user.services.impl;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.payroll.platform.PayrollException;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class UserFinder {
    
    @EJB
    EntityManagerWrapper managerWrapper;
    
    public User findUser(String username,String password){
        if (username!=null&& password!=null) {
            Map<String,Object> parameters = new HashMap<String, Object>();
            parameters.put("userName", username);
            parameters.put("password", password);
            return this.managerWrapper.getSingleResult(User.FIND_BY_USER_AND_PASSWORD, parameters);
        }
        throw new PayrollException("Cant FInd user");
    }
    
}
