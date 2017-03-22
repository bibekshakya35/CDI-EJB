/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.data.modules.user.services.impl.UserFinder;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class LoginService {
    @Inject
    UserFinder userFinder;
    
    public User getAuthentication(String username ,String password) throws Exception{
        String passwordEncryption =EncrptionUtils.encrypt(password);
        User userValidation = userFinder.findUser(username, passwordEncryption);
        return userValidation;
    } 
    
}
