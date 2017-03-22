/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.parkingcharge;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingCharge;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingChargeSetup;
import np.com.drose.parkgarau.modules.parkingmanagement.ParkingUnit;
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
public class ParkingChargeAddController extends BaseAddBean<ParkingChargeSetup> {

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

    ParkingCharge parkingCharge;

    @Inject
    transient IdentityBean identityBean;

    @Override
    @PostConstruct
    public void init() {
        if (!identityBean.hasAccess("PARK_CHARGE_SETUP_ADD")) {
            identityBean.authorize();
        }
        this.instance = new ParkingChargeSetup();
        companies = this.companyParkGarauService.getList();
        parkingCharge = new ParkingCharge();
        vehicleSubCategorys = this.vehicleSubCategoryParkGarauService.getList();

    }

    @Override
    public String onSave() {
        try {
            LOG.info("-------------------Parking charge Set up --------------------");
            LOG.log(Level.INFO, "parking charge setup : {0}", instance.toString());
            instance.setAuditInfo(new AuditInfo());
            parkingCharge.setAuditInfo(new AuditInfo());
            parkingCharge.setParkingChargeSetupCode(instance.getCode());
            LOG.log(Level.INFO, "Parking charge values : {0}", parkingCharge.toString());
            parkingChargeSetupParkGarauService.add(this.instance);
            parkingChargeServices.add(parkingCharge);
            FacesUtils.addMessage("message", "Parking Charge Setup", "Record " + instance.getCode() + " has been stored in database");
            return "pretty:parkfee_list";
        } catch (Exception e) {
            FacesUtils.addMessage("message", "Parking Charge Setup Error", e.getMessage());
            return "pretty:parkfee_add";
        }

    }

    public ParkingCharge getParkingCharge() {
        return parkingCharge;
    }

    public void setParkingCharge(ParkingCharge parkingCharge) {
        this.parkingCharge = parkingCharge;
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

   
}
