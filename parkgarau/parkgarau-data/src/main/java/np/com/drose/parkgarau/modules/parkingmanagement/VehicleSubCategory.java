package np.com.drose.parkgarau.modules.parkingmanagement;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 * The {@code VehicleSubType} class is the domain required for understanding
 * specific type of vehicle used under this System.
 *
 * @since Build {ParkGarau 1.0} (19 October 2016)
 * @author Bibek Shakya
 */
@Entity
@Table
@NamedQueries(value = {
    @NamedQuery(name = VehicleSubCategory.FIND_BY_VEHICLE_SUBTYPE_CODE, query = "select v from VehicleSubCategory v where v.code=:code"),
    @NamedQuery(name = VehicleSubCategory.FIND_VEHICLE_SUBTYPE_ALL, query = "select v from VehicleSubCategory v"),
    @NamedQuery(name = VehicleSubCategory.FIND_VSC_VEHICLECATEGORY,query = "SELECT v FROM VehicleSubCategory v WHERE v.vehicleCategoryCode=:code")
})
public class VehicleSubCategory implements Serializable, AbstractEntity {

    public static final String FIND_BY_VEHICLE_SUBTYPE_CODE = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_BY_VEHICLE_SUBTYPE_CODE";

    public static final String FIND_VEHICLE_SUBTYPE_ALL = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_VEHICLE_SUBTYPE_ALL";
    
    public static final String FIND_VSC_VEHICLECATEGORY="np.com.drose.parkgarau.modules.parkingmanagement.FIND_VSC_VEHICLECATEGORY";

    @Id
    @NotNull(message = "Vehicle Sub Type Code is mandatory and have to be unique")
    @Column(name = "vehiclesubtype_code", unique = true)
    private String code;

    private String name;

    private String description;

    private String vehicleCategoryCode;

    @Embedded
    private AuditInfo auditInfo;

    @Column(name = "is_active")
    private boolean active = true;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleSubCategory other = (VehicleSubCategory) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    public String getVehicleCategoryCode() {
        return vehicleCategoryCode;
    }

    public void setVehicleCategoryCode(String vehicleCategoryCode) {
        this.vehicleCategoryCode = vehicleCategoryCode;
    }

}
