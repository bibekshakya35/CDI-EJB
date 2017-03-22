/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.vehiclecategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.FilterService;
import np.com.drose.parkgarau.dto.filter.VehicleTypeFilter;
import np.com.drose.parkgarau.modules.parkingmanagement.VehicleCategory;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@ViewScoped
public class VehicleCategoryFilterController implements Serializable {

    private static final Logger LOG = Logger.getLogger(VehicleCategoryFilterController.class.getName());

    VehicleTypeFilter vehicleTypeFilter;

    @Inject
    FilterService<VehicleCategory> filterService;

    private List<VehicleCategory> vehicleTypes;

    @Inject
    transient IdentityBean identityBean;

    @PostConstruct
    public void init() {
        if (!identityBean.hasAccess("VEHICLE_CATEGORY_VIEW")) {
            this.identityBean.authorize();
        }
        vehicleTypeFilter = new VehicleTypeFilter();
        vehicleTypes = new ArrayList<>();
    }

    public List<VehicleCategory> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleCategory> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public void submit() {
        LOG.info(vehicleTypeFilter.toString());
        vehicleTypes = filterService.getResult(vehicleTypeFilter.getDetailQuery());
    }

    public VehicleTypeFilter getVehicleTypeFilter() {
        return vehicleTypeFilter;
    }

    public void setVehicleTypeFilter(VehicleTypeFilter vehicleTypeFilter) {
        this.vehicleTypeFilter = vehicleTypeFilter;
    }

}
