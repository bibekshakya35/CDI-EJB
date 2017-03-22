/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclecategory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;

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
public class VehicleCategoryAddController extends BaseAddBean<VehicleCategory> {

    @Inject
    ParkGarauService<VehicleCategory> parkGarauService;
    @Inject
    transient IdentityBean identityBean;
    AuditInfo auditInfo;
    
    @PostConstruct
    @Override
    protected void init() {
        super.init();
        if (!identityBean.hasAccess("VEHICLE_CATEGORY_ADD")) {
            identityBean.authorize();
        }
        this.instance = new VehicleCategory();
        auditInfo = new AuditInfo();
    }

    @Override
    public String onSave() {
        try {
            this.instance.setAuditInfo(auditInfo);
            this.parkGarauService.add(this.instance);
            FacesUtils.addMessage("messages", "Vehicle Type", "Record - " + this.instance.getCode() + " has been store in database");
            return "pretty:vehicletype_list";
        } catch (Exception e) {
            FacesUtils.addMessage("error", "Vehicle Type Error", "Record - " + e.getMessage());
            return "pretty:vehicletype_add";
        }
    }

}
