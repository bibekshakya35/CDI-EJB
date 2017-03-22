package np.com.drose.parkgarau.modules.parkingmanagement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 * The {@code ChargeSetup} class represent compositional relation between
 * {@code ParkFee} and {@code VehicleSubType} Object. This class helps to keep
 * record of fee for parking space according to the existing
 * {@code VehicleSubType} Object.
 *
 * @since Build {ParkGarau 1.0} (19 October 2016)
 * @author Bibek Shakya
 */
@Entity
@Table(name = "charges_setup")
@NamedQueries(value = {
    @NamedQuery(name = ParkingChargeSetup.FIND_BY_CHARGESETUPCODE, query = "select c from ParkingChargeSetup c where c.code=:code")
})
public class ParkingChargeSetup implements Serializable, AbstractEntity {

    public static final String FIND_BY_CHARGESETUPCODE = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_BY_CHARGESETUPCODE";
   

    @Id
    @Column(name = "chargesetup_code")
    private String code;

    @Column(name = "chargesetup_description")
    private String description;

    private double perUnitCharge;

    @Enumerated(EnumType.STRING)
    private ParkingUnit parkingUnit;

    @Column(name = "company_code")
    private String companyCode;

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

    public double getPerUnitCharge() {
        return perUnitCharge;
    }

    public void setPerUnitCharge(double perUnitCharge) {
        this.perUnitCharge = perUnitCharge;
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

    public ParkingUnit getParkingUnit() {
        return parkingUnit;
    }

    public void setParkingUnit(ParkingUnit parkingUnit) {
        this.parkingUnit = parkingUnit;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        return "ParkingChargeSetup{" + "code=" + code + ", description=" + description + ", perUnitCharge=" + perUnitCharge + ", parkingUnit=" + parkingUnit + ", companyCode=" + companyCode + ", auditInfo=" + auditInfo + ", active=" + active + '}';
    }
    @PrePersist
    public void init(){
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }
    

}
