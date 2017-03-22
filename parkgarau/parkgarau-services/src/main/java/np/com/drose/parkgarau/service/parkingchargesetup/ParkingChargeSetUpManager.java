/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.parkingchargesetup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingChargeSetup;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class ParkingChargeSetUpManager implements Manager<ParkingChargeSetup> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public ParkingChargeSetup finder(java.lang.Object code) {
        Map<String, Object> paramaters = new HashMap<>();
        paramaters.put("code",(String)code);
        return this.entityManagerWrapper.getSingleResult(ParkingChargeSetup.FIND_BY_CHARGESETUPCODE, paramaters);
    }


    @Override
    public List<ParkingChargeSetup> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
