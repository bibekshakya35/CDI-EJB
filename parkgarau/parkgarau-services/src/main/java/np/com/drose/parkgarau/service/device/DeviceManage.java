/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.device.Device;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class DeviceManage implements Manager<Device> {
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    @Override
    public Device finder(Object code) {
        Map<String,Object> map = new HashMap<>();
        map.put("code", (String)code);
        return this.entityManagerWrapper.getSingleResult(Device.FIND_BY_DEVICECODE, map);
    }

    @Override
    public List<Device> getList() {
        return this.entityManagerWrapper.findAll(Device.FIND_DEVICE_ALL);
    }
    
    
}
