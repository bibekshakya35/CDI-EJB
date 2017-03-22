/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclesubcategory;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;
import np.com.drose.parkgarau.pages.BaseAddBean;
import np.com.drose.parkgarau.security.FacesUtils;
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
public class VehicleSubCategoryAddController extends BaseAddBean<VehicleSubCategory> {

    @Inject
    ParkGarauService<VehicleSubCategory> parkGarauService;
    
    @Inject 
    ParkGarauService<VehicleCategory> vcParkGarauService;
    
    List<VehicleCategory> VehicleCategory;
    
    @Inject
    transient IdentityBean identityBean;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        if (!identityBean.hasAccess("VEHICLE_SUBCATEGORY_ADD")) {
            this.identityBean.authorize();
        }
        this.instance = new VehicleSubCategory();
        this.VehicleCategory = this.vcParkGarauService.getList();
    }

    @Override
    public String onSave() {
        try {
            this.parkGarauService.add(instance);
            FacesUtils.addMessage("messages", "Vehicle Type", "Record - " + instance.getCode() + " has been store in database");
            return "pretty:vehiclesubtype_list";
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "Record - " + e.getMessage());
            return "pretty:vehiclesubtype_add";
        }
    }

    public List<VehicleCategory> getVehicleCategory() {
        return VehicleCategory;
    }

    public void setVehicleCategory(List<VehicleCategory> VehicleCategory) {
        this.VehicleCategory = VehicleCategory;
    }
    
}
