/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.permission.Permission;

/**
 *
 * @author bibek
 */
@SuppressWarnings("serials")
@Entity
@Table(name = "roles")
@NamedQueries(value = {
    @NamedQuery(name = Role.FIND_BY_ROLE, query = "select r from Role r where r.code =:code"),
    @NamedQuery(name = Role.FIND_ALL, query = "select r from Role r")
})
public class Role implements Serializable, AbstractEntity {

    @Id
    @Column(name = "role_code", unique = true)
    private String code;

    @Transient
    private Company company;

    @NotNull
    @Column(name = "role_description")
    private String description;

    @Embedded
    private AuditInfo auditInfo;

    @Column(name = "is_active")
    private boolean active = true;

    public static final String FIND_BY_ROLE = "np.com.drose.parkgarau.modules.role.FIND_BY_ROLE";
    public static final String FIND_ALL = "np.com.drose.parkgarau.modules.role.FIND_ALL";

    @Transient
    private List<Permission> permissions;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }
}
