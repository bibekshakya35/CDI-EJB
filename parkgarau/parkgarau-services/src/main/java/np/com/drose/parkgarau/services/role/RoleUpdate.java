/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.role;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class RoleUpdate implements Updater<Role> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;
    
    @Inject
    Manager<Role> manager;

    @Override
    public void Update(Role t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }
        if (this.manager.finder(t.getCode())==null) {
            throw new ParkGarauException("Role doesnot exits in database");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(Role t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }
        if (this.manager.finder(t.getCode())!=null) {
            throw new ParkGarauException("Role already exits in database");
        } 
        this.entityManagerWrapper.persist(t);
    }
    

}
