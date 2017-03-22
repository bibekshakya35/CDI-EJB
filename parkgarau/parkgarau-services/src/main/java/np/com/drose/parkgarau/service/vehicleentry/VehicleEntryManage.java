/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.vehicleentry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleEntry;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class VehicleEntryManage implements Manager<VehicleEntry>{

    @EJB EntityManagerWrapper entityManagerWrapper;

    @Override
    public VehicleEntry finder(Object code) {
        Map<String,Object> paramaters = new HashMap<>();
        paramaters.put("code",(String) code);
        return this.entityManagerWrapper.getSingleResult(VehicleEntry.FIND_VEHICLEENTRY_BY_ID, paramaters);
    }

    @Override
    public List<VehicleEntry> getList() {
        return this.entityManagerWrapper.findAll(VehicleEntry.FIND_ALL_VEHICLEENTRY_ACTIVE);
    }
    
}
