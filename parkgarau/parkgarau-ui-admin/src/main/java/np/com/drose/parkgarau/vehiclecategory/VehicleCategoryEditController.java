/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclecategory;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;
import np.com.drose.parkgarau.pages.BaseEditBean;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;
import org.apache.commons.lang3.StringUtils;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named(value = "vehicleCategoryEditController")
@ViewScoped
public class VehicleCategoryEditController extends BaseEditBean<VehicleCategory> implements Serializable {

    private static final Logger LOG = Logger.getLogger(VehicleCategoryEditController.class.getName());

    @Inject
    ParkGarauService<VehicleCategory> parkGarauService;

    @Inject
    transient IdentityBean identityBean;
    
    AuditInfo auditInfo;

    @PostConstruct
    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if (!identityBean.hasAccess("VEHICLE_CATEGORY_UPDATE")) {
            this.identityBean.authorize();
        }
        auditInfo = new AuditInfo();
    }

    @Override
    public String onSave() {
        try {
            this.instance.setAuditInfo(auditInfo);
            LOG.log(Level.INFO, "instance {0}", instance.getDescription());
            this.parkGarauService.edit(this.instance);
            FacesUtils.addMessage("messages", "Vehicle Type", "Record - " + instance.getCode() + " has been edited");
            return "pretty:vehicletype_list";
        } catch (Exception e) {
            FacesUtils.addMessage("error", "Vehicle Type", e.getMessage());
            return "pretty:vehicletype_list";
        }
    }

    @Override
    public String onDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String onChangeActiveStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public void setSelectedItem(Object item) {
        this.selectedItem = item;
    }

    @Override
    public void loadSelectedItem() {
        if (this.instance != null) {
            return;
        }
        if (this.selectedItem != null && !StringUtils.isBlank(this.selectedItem.toString())) {
            this.instance = this.parkGarauService.finder(selectedItem.toString());
            LOG.log(Level.INFO, "instance after finding---------------> {0}", instance.toString());
            if (this.instance == null) {
                FacesUtils.addMessage("error", "Vehilce Type", "No record has been found");
            }
        }
    }

}
