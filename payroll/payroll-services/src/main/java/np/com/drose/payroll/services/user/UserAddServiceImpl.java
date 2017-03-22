/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.data.modules.user.services.impl.UserAddServices;
import np.com.drose.payroll.services.security.EncrptionUtils;

/**
 *
 * @author bibekshakya
 */
@LocalBean
@Stateless
public class UserAddServiceImpl {
    @Inject
    UserAddServices userAddServices;
    
    public void addUser(User user){
        try {
            user.setPassword(EncrptionUtils.encrypt(user.getPassword()));
            this.userAddServices.insertUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserAddServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
