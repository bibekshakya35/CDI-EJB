/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.company;

import java.io.Serializable;
import java.util.Date;
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
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "company_branch")
@NamedQueries(value = {
    @NamedQuery(name = CompanyBranch.FIND_ALL_COMPANY_BRANCH, query = "SELECT c FROM CompanyAdmin c"),
    @NamedQuery(name = CompanyBranch.FIND_COMPANYBRANCH_COMPANY_CODE, query = "SELECT c FROM CompanyAdmin c WHERE c.companyCode=:code")
})
public class CompanyBranch implements Serializable, AbstractEntity {

    public static final String FIND_BY_COMPANY_BRANCH_CODE = "np.com.drose.parkgarau.modules.company.FIND_BY_COMPANY_BRANCH_CODE";
    public static final String FIND_COMPANYBRANCH_COMPANY_CODE = "np.com.drose.parkgarau.modules.company.FIND_COMPANYBRANCH_COMPANY_CODE";
    public static final String FIND_ALL_COMPANY_BRANCH = "np.com.drose.parkgarau.modules.company.FIND_ALL_COMPANY_BRANCH";

    @Id
    @Column(name = "companybranch_code")
    private String branchCode;

    @Column(name = "companybranch_name")
    private String name;

    @Column(name = "company_branch")
    private String companyCode;

    private String location;

    private String contactPersonName;

    private String contactPersonNumber;

    @Embedded
    private AuditInfo auditInfo;

    @Column(name = "is_active")
    private boolean active = true;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonNumber() {
        return contactPersonNumber;
    }

    public void setContactPersonNumber(String contactPersonNumber) {
        this.contactPersonNumber = contactPersonNumber;
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

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }

}
