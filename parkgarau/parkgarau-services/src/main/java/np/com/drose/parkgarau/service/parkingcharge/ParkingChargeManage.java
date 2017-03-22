/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.parkingcharge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class ParkingChargeManage implements Manager<ParkingCharge> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public ParkingCharge finder(java.lang.Object code) {
        Map<String, Object> paramaters = new HashMap<>();
        if (code instanceof Integer) {
            paramaters.put("code", (Integer) code);
            return this.entityManagerWrapper.getSingleResult(ParkingCharge.FIND_BY_PARKFEECODE, paramaters);
        }
        if (code instanceof String) {
            paramaters.put("code", (Integer) code);
            return this.entityManagerWrapper.getSingleResult(ParkingCharge.FIND_PARKCHARGE_VEHICLESUB, paramaters);

        }
        return null;
    }

    @Override
    public List<ParkingCharge> getList() {
        return this.entityManagerWrapper.findAll(ParkingCharge.FIND_ALL_PARKFEE);
    }

}
