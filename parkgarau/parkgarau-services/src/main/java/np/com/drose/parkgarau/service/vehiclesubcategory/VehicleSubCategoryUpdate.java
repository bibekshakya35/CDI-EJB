/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclesubcategory;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;

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
public class VehicleSubCategoryUpdate implements Updater<VehicleSubCategory> {

    private static final Logger LOG = Logger.getLogger(VehicleSubCategoryUpdate.class.getName());

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<VehicleSubCategory> manager;

    @Override
    public void Update(VehicleSubCategory t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }

        if (manager.finder(t.getCode()) == null) {
            throw new ParkGarauException("Vehicle Type doesnot Exits");
        }
        this.entityManagerWrapper.merge(t);

    }

    @Override
    public void save(VehicleSubCategory t) {

        if (manager.finder(t.getCode()) != null) {
            throw new ParkGarauException("Vehicle Type already Exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
