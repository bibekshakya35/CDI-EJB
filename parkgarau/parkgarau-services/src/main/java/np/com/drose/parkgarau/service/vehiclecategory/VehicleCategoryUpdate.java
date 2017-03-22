/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclecategory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;

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
public class VehicleCategoryUpdate implements Updater<VehicleCategory> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<VehicleCategory> manager;

    @Override
    public void Update(VehicleCategory t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }

        if (manager.finder(t.getCode()) == null) {
            throw new ParkGarauException("Vehicle Type doesnot Exits");
        }
        this.entityManagerWrapper.merge(t);

    }

    @Override
    public void save(VehicleCategory t) {
        if (manager.finder(t.getCode()) != null) {
            throw new ParkGarauException("vehicle type does Exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
