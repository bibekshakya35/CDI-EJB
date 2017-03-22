/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.user;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.manager.UserUpdater;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.updater.UpdaterAll;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.platform.security.PasswordEncryptor;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class UserUpdate implements Updater<User>, UpdaterAll<User>,UserUpdater {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<User> manager;
    
    

    @Override
    public void Update(User t) {
        validators.forEach((v) -> {
            v.validate(t.getUserName());
        });
        if (manager.finder(t.getUserName()) == null) {
            throw new ParkGarauException("User doesnot exits");
        }
        this.entityManagerWrapper.merge(t);
    }
    private static final Logger LOG = Logger.getLogger(UserUpdate.class.getName());

    @Override
    public void save(User t) {
        validators.forEach((v) -> {
            v.validate(t.getUserName());
        });
        if (manager.finder(t.getUserName()) != null) {
            throw new ParkGarauException("User already exits");
        }
        this.entityManagerWrapper.persist(t);
    }

    @Override
    public void addAll(List<User> t) {
        t.stream().forEach((user) -> {
            validators.forEach((v) -> {
                v.validate(user.getUserName());
            });
            if (manager.finder(user.getUserName()) != null) {
                throw new ParkGarauException("User already exits");
            }
        });
        this.entityManagerWrapper.addAll(t);
    }

    @Override
    public void deleteAll(List<User> t) {
        this.entityManagerWrapper.deleteAll(t);
    }

    @Override
    public void remove(Object t) {
        this.entityManagerWrapper.remove(t);
    }

    @Override
    public void updateAll(List<User> t) {
        t.stream().forEach((user) -> {
            validators.forEach((v) -> {
                v.validate(user.getUserName());
            });
            if (manager.finder(user.getUserName()) == null) {
                throw new ParkGarauException("user doesnot exist");
            }
        });
        this.entityManagerWrapper.editAll(t);
    }

    @Override
    public boolean changedPassword(String username, String newPassword, String oldPassword) {
        User user = this.manager.finder(username);
        if (user==null) {
            LOG.info("USER CANNOT FIND");
            throw new ParkGarauException("CANNOT FIND USER");
        }
        if (!PasswordEncryptor.isEquals((String)user.getUserPassword(), (String)oldPassword)) {
            LOG.info("well db and password dont match");
            throw new ParkGarauException("password!!! you have enter did not match ");
        }
        user.setUserPassword(PasswordEncryptor.encode((String)newPassword));
        LOG.log(Level.INFO, "AFTER MATCH AND ALL SHIT BEEN THRU {0}", user.getUserPassword());
        this.Update(user);
        LOG.info("SUCCESSS FROM CHANGEPassword Service");
        return true;
    }
    

}
