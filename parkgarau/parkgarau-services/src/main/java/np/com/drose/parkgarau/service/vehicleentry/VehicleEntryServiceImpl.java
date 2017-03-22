/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehicleentry;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.updater.UpdaterAll;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class VehicleEntryServiceImpl implements ParkGarauService<VehicleEntry> {

    @Inject
    Manager<VehicleEntry> manager;
    @Inject
    Updater<VehicleEntry> updater;
    @Inject
    UpdaterAll<VehicleEntry> updaterAll;

    @Override
    public void add(VehicleEntry t) {
        this.updater.save(t);
    }
    
    @Override
    public void addAll(List<VehicleEntry> t) {
        this.updaterAll.addAll(t);
    }
    
    @Override
    public void deleteAll(List<VehicleEntry> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void edit(VehicleEntry t) {
        this.updater.Update(t);
    }
    
    @Override
    public List<VehicleEntry> getList() {
        return this.manager.getList();
    }
    
    @Override
    public VehicleEntry finder(Object code) {
        return this.manager.finder(code);
    }
    
    @Override
    public List<VehicleEntry> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void editAll(List<VehicleEntry> t) {
        this.updaterAll.updateAll(t);
    }
    
}
