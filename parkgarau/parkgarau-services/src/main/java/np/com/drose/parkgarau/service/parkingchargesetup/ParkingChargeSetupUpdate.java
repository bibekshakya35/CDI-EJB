/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.parkingchargesetup;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingChargeSetup;
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
public class ParkingChargeSetupUpdate implements Updater<ParkingChargeSetup> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<ParkingChargeSetup> manager;

    @Override
    public void Update(ParkingChargeSetup t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }

        if (manager.finder(t.getCode()) == null) {
            throw new ParkGarauException("Charge Setup doesnot Exits");
        }
        this.entityManagerWrapper.merge(t);

    }

    @Override
    public void save(ParkingChargeSetup t) {
        for (GeneralValidator validator : validators) {
            validator.validate(t.getCode());
        }
        if (manager.finder(t.getCode()) != null) {
            throw new ParkGarauException("Charge Setup does Exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
