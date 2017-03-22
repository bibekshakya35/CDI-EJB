/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.device;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.modules.device.Device;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class DeviceServiceImpl implements ParkGarauService<Device>{
    @Inject
    Manager<Device> manager;
    
    @Inject
    Updater<Device> updater;
    
    @Override
    public void add(Device t) {
        updater.save(t);
    }

    @Override
    public void addAll(List<Device> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Device> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Device t) {
        updater.Update(t);
    }

    @Override
    public List<Device> getList() {
        return manager.getList();
    }

    @Override
    public Device finder(Object code) {
        return this.manager.finder(code);
    }

    @Override
    public List<Device> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
