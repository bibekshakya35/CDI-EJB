/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.modules.device;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "devices")
@NamedQueries(
        value ={
            @NamedQuery(name = Device.FIND_BY_DEVICECODE,query = "SELECT d FROM Device d WHERE d.deviceCode =:code"),
            @NamedQuery(name = Device.FIND_DEVICE_ALL,query = "SELECT d FROM Device d WHERE d.active=TRUE")
        }
)
public class Device implements Serializable,AbstractEntity{
    
    public static final String FIND_BY_DEVICECODE="np.com.drose.parkgarau.modules.device.FIND_BY_DEVICECODE";
    public static final String FIND_DEVICE_ALL="np.com.drose.parkgarau.modules.device.FIND_DEVICE_ALL";
            
    @Id
    @Column(name = "device_code",unique = true)
    private String deviceCode;
    
    @Column(name = "device_type")
    private String deviceType;
    
    @Column(name = "device_model")
    private String model;
    
    @Column(name = "device_description")
    private String description;
    
    @Column(name = "company_code")
    private String companyCode;
    
    @Column(name = "company_branch")
    private String companyBranchCode;
    
    @Embedded
    private AuditInfo auditInfo;
    
    @Column(name = "is_active")
    private boolean active=true;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
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
        hash = 53 * hash + Objects.hashCode(this.deviceCode);
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
        final Device other = (Device) obj;
        if (!Objects.equals(this.deviceCode, other.deviceCode)) {
            return false;
        }
        return true;
    }
    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }
    
    
    
}
