/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclesubcategory;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class VehicleSubCategoryServiceImpl implements ParkGarauService<VehicleSubCategory> {

    @Inject
    Manager<VehicleSubCategory> manager;

    @Inject
    Updater<VehicleSubCategory> updater;

    @Override
    public void add(VehicleSubCategory t) {
        this.updater.save(t);
    }

    @Override
    public void edit(VehicleSubCategory t) {
        this.updater.Update(t);
    }

    @Override
    public List<VehicleSubCategory> getList() {
        return this.manager.getList();
    }

    @Override
    public VehicleSubCategory finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public void addAll(List<VehicleSubCategory> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<VehicleSubCategory> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VehicleSubCategory> findList(java.lang.Object code) {
        return this.manager.findAll(code);
    }
    
}
