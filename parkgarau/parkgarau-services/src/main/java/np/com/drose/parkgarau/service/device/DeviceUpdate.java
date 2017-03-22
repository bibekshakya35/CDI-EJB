/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.device;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.modules.device.Device;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class DeviceUpdate implements Updater<Device>{
    
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    
    @Inject
    Instance<GeneralValidator> generalValidators;
    
    @Inject
    Manager<Device> manager;
    
    @Override
    public void Update(Device t) {
        generalValidators.forEach((v)->{
            v.validate(t.getDeviceCode());
        });
        if (this.manager.finder(t.getDeviceCode())==null) {
            throw new ParkGarauException("Device doesnot Exists");
        }
        this.entityManagerWrapper.persist(t);
    }

    @Override
    public void save(Device t) {
        generalValidators.forEach((v)->{
            v.validate(t.getDeviceCode());
        });
        if (this.manager.finder(t.getDeviceCode())!=null) {
            throw new ParkGarauException("Device Already Exists");
        }
        this.entityManagerWrapper.persist(t);
    }
    
}
