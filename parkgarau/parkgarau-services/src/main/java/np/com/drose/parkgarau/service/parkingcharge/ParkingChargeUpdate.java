/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.parkingcharge;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
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
public class ParkingChargeUpdate implements Updater<ParkingCharge> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<ParkingCharge> manager;

    @Override
    public void Update(ParkingCharge t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getId());
        }

        if (manager.finder(t.getId()) == null) {
            throw new ParkGarauException("ParkFee doesnot Exits");
        }
        this.entityManagerWrapper.merge(t);

    }

    @Override
    public void save(ParkingCharge t) {
        if (manager.finder(t.getId()) != null) {
            throw new ParkGarauException("Park Fee does Exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
