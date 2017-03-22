/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.parkingcharge;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class ParkingChargeServiceImpl implements ParkGarauService<ParkingCharge> {

    @Inject
    Manager<ParkingCharge> manager;

    @Inject
    Updater<ParkingCharge> updater;

    @Override
    public void add(ParkingCharge t) {
        this.updater.save(t);
    }

    @Override
    public void edit(ParkingCharge t) {
        this.updater.Update(t);
    }

    @Override
    public List<ParkingCharge> getList() {
        return this.manager.getList();
    }

    @Override
    public ParkingCharge finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public void addAll(List<ParkingCharge> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<ParkingCharge> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ParkingCharge> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
