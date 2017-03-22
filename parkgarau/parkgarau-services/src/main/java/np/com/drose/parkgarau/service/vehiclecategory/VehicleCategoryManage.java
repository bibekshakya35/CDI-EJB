/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclecategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;


/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
@SuppressWarnings("override")
public class VehicleCategoryManage implements Manager<VehicleCategory> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public VehicleCategory finder(java.lang.Object code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code",(String)code);
        return this.entityManagerWrapper.getSingleResult(VehicleCategory.VEHICLE_TYPE_FIND_BY_CODE, parameters);
    }

    

    @Override
    public List<VehicleCategory> getList() {
        return this.entityManagerWrapper.findAll(VehicleCategory.VEHICLE_TYPE_FIND_ALL);
    }
    

}
