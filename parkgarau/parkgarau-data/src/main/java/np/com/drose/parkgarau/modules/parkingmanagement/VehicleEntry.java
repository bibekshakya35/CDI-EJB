/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.parkingmanagement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "tbl_vehicleentry")
@NamedQueries(value = {
    @NamedQuery(name = VehicleEntry.FIND_VEHICLEENTRY_BY_ID, query = "SELECT ve FROM VehicleEntry ve WHERE ve.id=:code AND ve.active=TRUE"),
    @NamedQuery(name = VehicleEntry.FIND_ALL_VEHICLEENTRY_ACTIVE, query = "SELECT ve FROM VehicleEntry ve")
})
public class VehicleEntry implements Serializable, AbstractEntity {

    public static final String FIND_VEHICLEENTRY_BY_ID = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_VEHICLEENTRY_BY_ID";
    public static final String FIND_ALL_VEHICLEENTRY_ACTIVE = "np.com.drose.parkgarau.modules.parkingmanagement.FIND_ALL_VEHICLEENTRY_ACTIVE";

    @Id
    @Column(name = "vehicleentry_id")
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date enteredOn;
    @NotNull
    private String vehicleNumber;
    @NotNull
    private String vehicleSubCategory;
    @NotNull
    private String enteredBy;

    private double duration;

    private double charge;

    @Temporal(TemporalType.TIMESTAMP)
    private Date exitedOn;

    private String exitedBy;

    @Column(name = "is_active")
    private boolean active = true;

    @Embedded
    private AuditInfo auditInfo;

    public VehicleEntry() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEnteredOn() {
        return enteredOn;
    }

    public void setEnteredOn(Date enteredOn) {
        this.enteredOn = enteredOn;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleSubCategory() {
        return vehicleSubCategory;
    }

    public void setVehicleSubCategory(String vehicleSubCategory) {
        this.vehicleSubCategory = vehicleSubCategory;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public Date getExitedOn() {
        return exitedOn;
    }

    public void setExitedOn(Date exitedOn) {
        this.exitedOn = exitedOn;
    }

    public String getExitedBy() {
        return exitedBy;
    }

    public void setExitedBy(String exitedBy) {
        this.exitedBy = exitedBy;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedBy(enteredBy);
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        this.setActive(false);
    }

    public VehicleEntry(String id, Date enteredOn, String vehicleNumber, String vehicleSubCategory, String enteredBy, AuditInfo auditInfo) {
        this.id = id;
        this.enteredOn = enteredOn;
        this.vehicleNumber = vehicleNumber;
        this.vehicleSubCategory = vehicleSubCategory;
        this.enteredBy = enteredBy;
        this.auditInfo = auditInfo;
    }

    @Override
    public String toString() {
        return "VehicleEntry{" + "id=" + id + ", enteredOn=" + enteredOn + ", vehicleNumber=" + vehicleNumber + ", vehicleSubCategory=" + vehicleSubCategory + ", enteredBy=" + enteredBy + ", duration=" + duration + ", charge=" + charge + ", exitedOn=" + exitedOn + ", exitedBy=" + exitedBy + ", active=" + active + ", auditInfo=" + auditInfo + '}';
    }

}
