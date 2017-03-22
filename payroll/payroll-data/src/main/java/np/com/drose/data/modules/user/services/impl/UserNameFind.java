/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.user.services.impl;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.user.domain.User;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class UserNameFind {
    
    @EJB
    EntityManagerWrapper managerWrapper;
    
  
    public User findUserName(String userName){
       Map<String,Object> parameters = new HashMap<String, Object>();
       parameters.put("userName", userName);
       return this.managerWrapper.getSingleResult(User.FIND_BY_USERNAME, parameters);
    }
    
}
