/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.vehiclesubcategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class VehicleSubCategoryManage implements Manager<VehicleSubCategory> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public VehicleSubCategory finder(java.lang.Object code) {
        Map<String, Object> paramaters = new HashMap<>();
        paramaters.put("code", (String) code);
        return this.entityManagerWrapper.getSingleResult(VehicleSubCategory.FIND_BY_VEHICLE_SUBTYPE_CODE, paramaters);
    }

    @Override
    public List<VehicleSubCategory> getList() {
        return this.entityManagerWrapper.findAll(VehicleSubCategory.FIND_VEHICLE_SUBTYPE_ALL);
    }

    @Override
    public List<VehicleSubCategory> findAll(java.lang.Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", (String) code);
        return this.entityManagerWrapper.findAll(VehicleSubCategory.FIND_VSC_VEHICLECATEGORY, parameters);
    }

}
