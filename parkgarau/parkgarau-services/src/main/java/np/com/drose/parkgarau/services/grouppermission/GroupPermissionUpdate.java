/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.services.grouppermission;

import javax.ejb.EJB;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class GroupPermissionUpdate implements Updater<GroupPermission>{

    @EJB
    EntityManagerWrapper entityManagerWrapper;
    
    @Inject
    Instance<GeneralValidator> validators;
    
    @Inject
    Instance<Manager<GroupPermission>> managers;
     
    @Override
    public void Update(GroupPermission t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }
        managers.forEach((manager)-> {
            if (manager.finder(t.getCode())==null) {
                throw new ParkGarauException("Group Permission doesnot Exists");
            }
        });
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(GroupPermission t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }
        managers.forEach((manager)->{
            if (manager.finder(t.getCode())!=null) {
                throw new ParkGarauException("Group Permission already Exists");
            }
        });
        this.entityManagerWrapper.persist(t);  
    }
    
}
