/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclesubcategory;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleSubCategory;
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
@Named
@ViewScoped
public class VehicleSubCategoryEditController extends BaseEditBean<VehicleSubCategory> implements Serializable {

    @Inject
    ParkGarauService<VehicleSubCategory> parkGarauService;

    @Inject
    ParkGarauService<VehicleCategory> vCParkGarauService;

    List<VehicleCategory> vehicleCatergory;

    private static final Logger LOG = Logger.getLogger(VehicleSubCategoryEditController.class.getName());

    @Override
    public String onSave() {
        try {
            this.parkGarauService.edit(this.instance);
            FacesUtils.addMessage("messages", "Vehicle Type ", "Record - " + this.instance.getCode() + " has been edited ");
            return "pretty:vehiclesubtype_list";
        } catch (Exception e) {
            FacesUtils.addMessage("error", "Vehicle Type ", e.getMessage());
            return "pretty:vehiclesubtype_edit";
        }
    }
    @Inject
    transient IdentityBean identityBean;

    @PostConstruct
    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates
        if (!identityBean.hasAccess("VEHICLE_SUBCATEGORY_UPDATE")) {
            this.identityBean.authorize();
        }
        vehicleCatergory = this.vCParkGarauService.getList();

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
            LOG.log(Level.INFO, "selected code{0}", selectedItem);
            this.instance = this.parkGarauService.finder(this.selectedItem.toString());
            if (this.instance == null) {
                FacesUtils.addMessage("error", "Vehicle Type", "Unable to find record");
                return;
            }
        }
    }

    public List<VehicleCategory> getVehicleCatergory() {
        return vehicleCatergory;
    }

    public void setVehicleCatergory(List<VehicleCategory> vehicleCatergory) {
        this.vehicleCatergory = vehicleCatergory;
    }

}
