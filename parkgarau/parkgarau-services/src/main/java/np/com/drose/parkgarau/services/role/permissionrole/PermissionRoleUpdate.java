/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.services.role.permissionrole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.PermissionRole;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.updater.UpdaterAll;
import np.com.drose.parkgarau.api.validator.GeneralValidator;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class PermissionRoleUpdate implements Updater<PermissionRole>,UpdaterAll<PermissionRole>{
    
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    
    @Inject
    Instance<GeneralValidator> generalValidators;

    @Inject
    Manager<PermissionRole> manager;
    
    @Override
    public void Update(PermissionRole t) {
        for (GeneralValidator generalValidator : generalValidators) {
            generalValidator.validate(t.getId());
        }
        if (this.manager.finder(t.getId())==null) {
            throw new ParkGarauException("PermissionRole doesnot  Exist");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(PermissionRole t) {
        for (GeneralValidator generalValidator : generalValidators) {
            generalValidator.validate(t.getId());
        }
        if (this.manager.finder(t.getId())!=null) {
            throw new ParkGarauException("PermissionRole already Exist");
        }
        this.entityManagerWrapper.persist(t);
    }

    @Override
    public void addAll(List<PermissionRole> t) {
        this.entityManagerWrapper.addAll(t);
    }

    @Override
    public void deleteAll(List<PermissionRole> t) {
        this.entityManagerWrapper.deleteAll(t);
    }

    @Override
    public void updateAll(List<PermissionRole> t) {
        this.entityManagerWrapper.editAll(t);
    }

    @Override
    public void remove(Object t) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code", t);
        this.entityManagerWrapper.deleteByOtherId(PermissionRole.DELETE_BY_ROLEID, parameters);
    }

    
    
   
   
    

}
