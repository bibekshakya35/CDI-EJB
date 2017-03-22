/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.permission;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.permission.Permission;
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
public class PermissionUpdate implements Updater<Permission> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<Permission> managers;

    @Override
    public void Update(Permission t) {
        validators.forEach((v) -> {
            v.validate(t.getCode());
        });
        if (managers.finder(t.getCode()) == null) {
            throw new ParkGarauException("Permission doesnot exits");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(Permission t) {
        validators.forEach((v) -> {
            v.validate(t.getCode());
        });
        if (managers.finder(t.getCode()) != null) {
            throw new ParkGarauException("Permission Already exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
