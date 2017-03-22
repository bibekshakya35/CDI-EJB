/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclesubcategory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;
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
public class VehicleSubCategoryListController extends BaseListBean<VehicleSubCategory> {

    @Inject
    ParkGarauService<VehicleSubCategory> parkGarauService;

    @Inject
    transient IdentityBean identityBean;

    private int count = 0;

    @Override
    public void fillList() {
        if (!identityBean.hasAccess("VEHICLE_SUBCATEGORY_VIEW")) {
            this.identityBean.authorize();
        }
        this.list = this.parkGarauService.getList();
        if (!list.isEmpty()) {
            this.count = list.size();
        }
    }

    public int getCount() {
        return count;
    }

}
