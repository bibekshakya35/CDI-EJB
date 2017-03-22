/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.parkingmanagement;

import java.io.Serializable;
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
 * The {@code VehicleType} is the domain required for understanding type of
 * vehicle present under the System.
 *
 * @since Build {ParkGarau 1.0} (19 October 2016)
 * @author Bibek Shakya
 */
@Entity
@Table(name = "vehicletypes")
@SuppressWarnings("serial")
@NamedQueries(value = {
    @NamedQuery(name = VehicleCategory.VEHICLE_TYPE_FIND_BY_CODE, query = "Select v from VehicleCategory v where v.code=:code"),
    @NamedQuery(name = VehicleCategory.VEHICLE_TYPE_FIND_ALL, query = "select v from VehicleCategory v")
})
public class VehicleCategory implements Serializable, AbstractEntity {

    public static final String VEHICLE_TYPE_FIND_ALL = "np.com.drose.parkgarau.modules.parkingmanagement.VEHICLE_TYPE_FIND_ALL";

    public static final String VEHICLE_TYPE_FIND_BY_CODE = "np.com.drose.parkgarau.modules.parkingmanagement.VEHICLE_TYPE_FIND_BY_CODE";

    @Id
    @NotNull(message = "Code need to be set for each new Vechicle types")
    @Column(name = "code", unique = true)
    private String code;
    
    private String name;

    @NotNull(message = "Description about vehicle type must provided")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" + "code=" + code + ", description=" + description + ", auditInfo=" + auditInfo + ", active=" + active + '}';
    }

}
