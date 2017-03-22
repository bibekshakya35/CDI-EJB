/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.company;

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
import javax.validation.constraints.Pattern;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.user.User;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "companies")
@NamedQueries(value = {
    @NamedQuery(name = Company.FIND_BY_CCODE, query = "select c from Company c where c.code=:code"),
    @NamedQuery(name = Company.FIND_BY_ALL, query = "Select c from Company c")
})
public class Company implements Serializable,AbstractEntity {

    public static final String FIND_BY_CCODE = "np.com.drose.parkgarau.modules.company.FIND_BY_CCODE";
    public static final String FIND_BY_ALL = "np.com.drose.parkgarau.modules.company.FIND_BY_ALL";

    @Id
    @NotNull
    @Column(name = "company_code", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(unique = true, name = "company_name")
    private String companyName;

    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid Email address")
    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;



    @NotNull
    @Column(name = "ordered_by")
    private int orderBy;
    
    @Transient
    private List<User> adminUsers;
    
    @Embedded
    private AuditInfo auditInfo;
    
    @Column(name = "is_active")
    private boolean active=true;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {

        this.addressLine2 = addressLine2;
    }

    public Company() {

    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

   
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<User> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<User> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @Override
    public String toString() {
        return "Company{" + "code=" + code + ", productName=" + productName + ", companyName=" + companyName + ", companyEmail=" + companyEmail + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", orderBy=" + orderBy + ", adminUsers=" + adminUsers + ", auditInfo=" + auditInfo + ", active=" + active + '}';
    }

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }
   
}
