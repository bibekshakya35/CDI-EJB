package np.com.drose.parkgarau.modules.parkingmanagement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 * The {@code ParkFee} class represent the object that contain fee structure for
 * Parking System.
 *
 * @since Build {ParkGarau 1.0} (19 October 2016)
 * @author Bibek Shakya
 */
@Entity
@Table
@NamedQueries(value = {
    @NamedQuery(name = ParkingCharge.FIND_BY_PARKFEECODE, query = "select p from ParkingCharge p where p.id=:code"),
    @NamedQuery(name = ParkingCharge.FIND_ALL_PARKFEE, query = "select p from ParkingCharge p"),
    @NamedQuery(name = ParkingCharge.FIND_PARKCHARGE_VEHICLESUB,query = "Select p FROM ParkingCharge p WHERE p.vehicleSubCategoryCode=:code")
    
})
public class ParkingCharge implements Serializable, AbstractEntity {

    public static final String FIND_BY_PARKFEECODE = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_BY_PARKFEECODE";
    public static final String FIND_ALL_PARKFEE = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_ALL_PARKFEE";
    public static final String FIND_PARKCHARGE_VEHICLESUB="np.com.drose.parkgarau.modules.parkingmanagement.PARKCHARGE_VEHICLESUB";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parkingcharge_id")
    private int id;

    /**
     * code of {@code VehicleSubCategory} object
     */
    private String vehicleSubCategoryCode;

    /**
     * code of {@code ParkingSetup} object
     */
    private String parkingChargeSetupCode;

 

    @Embedded
    private AuditInfo auditInfo;

    @Column(name = "is_active")
    private boolean active=true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleSubCategoryCode() {
        return vehicleSubCategoryCode;
    }

    public void setVehicleSubCategoryCode(String vehicleSubCategoryCode) {
        this.vehicleSubCategoryCode = vehicleSubCategoryCode;
    }

    public String getParkingChargeSetupCode() {
        return parkingChargeSetupCode;
    }

    public void setParkingChargeSetupCode(String parkingChargeSetupCode) {
        this.parkingChargeSetupCode = parkingChargeSetupCode;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
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
        int hash = 3;
        hash = 29 * hash + this.id;
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
        final ParkingCharge other = (ParkingCharge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "ParkingCharge{" + "id=" + id + ", vehicleSubCategoryCode=" + vehicleSubCategoryCode + ", parkingChargeSetupCode=" + parkingChargeSetupCode +  ", auditInfo=" + auditInfo + ", active=" + active + '}';
    }

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }
    
}
