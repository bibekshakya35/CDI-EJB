/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.user.services.impl;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.data.modules.user.services.UserValidation;
import np.com.drose.payroll.platform.PayrollException;

/**
 *
 * @author bibekshakya
 */
@Stateless
@LocalBean
public class UserAddServices {
    
    
    @EJB
    EntityManagerWrapper managerWrapper;
    
    private static final Logger LOG = Logger.getLogger(UserAddServices.class.getName());
    
    @Inject
    UserNameFind finder;
    
    @Inject
    Instance<UserValidation> validations;
    
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertUser(User user){
        for (UserValidation validation : validations) {
            validation.validation(user);
        }
        if(finder.findUserName(user.getUserName())!=null){
            throw new PayrollException("User name already Exist");
        }
        this.managerWrapper.persist(user);
    }
}
