/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.parkingcharge;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
import np.com.drose.parkgarau.pages.BaseListBean;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class ParkingChargeListController extends BaseListBean<ParkingCharge> implements Serializable {

    @Inject
    ParkGarauService<ParkingCharge> service;

    @Inject transient IdentityBean identityBean;
    
    @Override
    public void fillList() {
        if(!identityBean.hasAccess("PARK_CHARGE_SETUP_VIEW")){
           identityBean.authorize();
        }
         this.list = this.service.getList();
    }

}
