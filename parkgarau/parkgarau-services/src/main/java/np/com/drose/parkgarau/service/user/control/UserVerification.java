/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.user.control;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.platform.security.PasswordEncryptor;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class UserVerification {
    @Inject ParkGarauService<User> userFinder;
    private static final Logger LOG = Logger.getLogger(UserVerification.class.getName());
   
    public User validateUser(String userName,String password){
        Optional<User> user = Optional.ofNullable(this.userFinder.findWithActiveOne(userName));
        if (!user.isPresent()) {
            throw  new ParkGarauException("Incorrect username");
        }
        User u = user.get();
        LOG.log(Level.INFO, "user :: getRole {0}", u.getRoleId());
        LOG.log(Level.INFO, "user:: userType {0}", u.getUserType());
        if (!PasswordEncryptor.isEquals((String)u.getUserPassword(),(String)password)) {
             throw  new ParkGarauException("Incorrect password or Email Address "); 
        }
        return u;
    }
    
    
}
