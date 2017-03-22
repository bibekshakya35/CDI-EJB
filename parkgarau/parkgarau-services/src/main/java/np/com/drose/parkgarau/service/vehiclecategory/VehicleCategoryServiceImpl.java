/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclecategory;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;

import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class VehicleCategoryServiceImpl implements ParkGarauService<VehicleCategory> {

    @Inject
    Manager<VehicleCategory> manager;

    @Inject
    Updater<VehicleCategory> updater;

    @Override
    public void add(VehicleCategory t) {
        this.updater.save(t);
    }

    @Override
    public void edit(VehicleCategory t) {
        this.updater.Update(t);
    }

    @Override
    public List<VehicleCategory> getList() {
        return this.manager.getList();
    }

    @Override
    public VehicleCategory finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public void addAll(List<VehicleCategory> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<VehicleCategory> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VehicleCategory> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
