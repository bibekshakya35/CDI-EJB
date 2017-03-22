/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehicleentry;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.updater.UpdaterAll;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class VehicleEntryUpdate implements Updater<VehicleEntry>, UpdaterAll<VehicleEntry> {
    
    @Inject
    Manager<VehicleEntry> manager;
    @Inject
    Instance<GeneralValidator> validators;
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    
    @Override
    public void Update(VehicleEntry t) {
        validators.forEach((ve)->{
            ve.validate(t.getId());
        });
        if (this.manager.finder(t.getId())==null) {
            throw new ParkGarauException("Record doesnot Exist");
        }
        this.entityManagerWrapper.merge(t);
    }
    
    @Override
    public void save(VehicleEntry t) {
        validators.forEach((ve)->{
            ve.validate(t.getId());
        });
        if (this.manager.finder(t.getId())!=null) {
            throw new ParkGarauException("Record Already Exist");
        }
        this.entityManagerWrapper.persist(t);
    
    }
    
    @Override
    public void addAll(List<VehicleEntry> t) {
        t.stream().forEach((ve) -> {
            validators.forEach((v) -> {
                v.validate(ve.getId());
            });
            if (this.manager.finder(ve.getId()) != null) {
                Update(ve);
            }
        });
        this.entityManagerWrapper.addAll(t);
    }
    
    @Override
    public void deleteAll(List<VehicleEntry> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void updateAll(List<VehicleEntry> t) {
        t.stream().forEach((ve) -> {
            validators.forEach((v) -> {
                v.validate(ve.getId());
            });
            if (this.manager.finder(ve.getId()) == null) {
                save(ve);
            }
        });
        this.entityManagerWrapper.editAll(t);
    }
    
}
