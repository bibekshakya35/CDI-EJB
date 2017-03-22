/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.parkingcharge;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingChargeSetup;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingUnit;
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
public class ParkingChargeEditController extends BaseEditBean<ParkingCharge> implements Serializable {

    private static final Logger LOG = Logger.getLogger(ParkingChargeAddController.class.getName());

    @Inject
    ParkGarauService<ParkingCharge> parkingChargeServices;

    @Inject
    ParkGarauService<ParkingChargeSetup> parkingChargeSetupParkGarauService;

    @Inject
    ParkGarauService<VehicleSubCategory> vehicleSubCategoryParkGarauService;

    @Inject
    ParkGarauService<Company> companyParkGarauService;

    List<VehicleSubCategory> vehicleSubCategorys;

    List<Company> companies;

    public ParkingUnit[] getParkingUnit() {
        return ParkingUnit.values();
    }

    ParkingChargeSetup parkingChargeSetup;

    @Inject
    transient IdentityBean identityBean;

    @Override
    @PostConstruct
    public void init() {
        super.init();
        if (!identityBean.hasAccess("PARK_CHARGE_SETUP_UPDATE")) {
            identityBean.authorize();
        }
        companies = this.companyParkGarauService.getList();
        vehicleSubCategorys = this.vehicleSubCategoryParkGarauService.getList();
    }

    @Override
    public String onSave() {
        try {
            LOG.info("-------------------Parking charge Set up --------------------");
            LOG.log(Level.INFO, "parking charge setup : {0}", instance.toString());
            instance.setParkingChargeSetupCode(parkingChargeSetup.getCode());
            LOG.log(Level.INFO, "Parking charge values : {0}", instance.toString());
            parkingChargeSetupParkGarauService.edit(parkingChargeSetup);
            parkingChargeServices.edit(instance);
            FacesUtils.addMessage("message", "Parking Charge Setup", "Record " + parkingChargeSetup.getCode() + " has been edited in database");
            return "pretty:parkfee_list";
        } catch (Exception e) {
            FacesUtils.addMessage("message", "Parking Charge Setup Error", e.getMessage());
            return "pretty:parkfee_edit";
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
        return selectedItem;
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
        if (this.selectedItem != null && StringUtils.isNotBlank(this.selectedItem.toString())) {
            this.instance = parkingChargeServices.finder(Integer.parseInt(this.selectedItem.toString()));
            this.parkingChargeSetup = this.parkingChargeSetupParkGarauService.finder(instance.getParkingChargeSetupCode());
            if (this.instance == null) {
                FacesUtils.addMessage("error", "Parking Charge Edit", "No record has been found");
            }
        }
    }

    public List<VehicleSubCategory> getVehicleSubCategorys() {
        return vehicleSubCategorys;
    }

    public void setVehicleSubCategorys(List<VehicleSubCategory> vehicleSubCategorys) {
        this.vehicleSubCategorys = vehicleSubCategorys;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public ParkingChargeSetup getParkingChargeSetup() {
        return parkingChargeSetup;
    }

    public void setParkingChargeSetup(ParkingChargeSetup parkingChargeSetup) {
        this.parkingChargeSetup = parkingChargeSetup;
    }

}
