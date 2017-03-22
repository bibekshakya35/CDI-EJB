/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.userprofile.UserProfile;

/**
 *
 * @author bibek
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "users")
@NamedQueries(value = {
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.userName=:userName"),
    @NamedQuery(name = User.FIND_ALL, query = "select u from User u"),
    @NamedQuery(name = User.FIND_BY_USERNAME_ACTIVE,query = "SELECT u FROM User u WHERE u.userName=:userName AND u.active=TRUE"),
    @NamedQuery(name = User.LIST_SYSTEM_ADMIN, query = "SELECT u from User u WHERE u.roleId='SYS_ADMIN' AND u.active=TRUE")
})
public class User implements Serializable, AbstractEntity {

    public static final String FIND_BY_USERNAME = "np.com.drose.parkgarau.modules.user.FIND_BY_USERNAME";
    public static final String FIND_ALL = "np.com.drose.parkgarau.modules.user.FIND_ALL";
    public static final String FIND_BY_USERNAME_ACTIVE = "np.com.drose.parkgarau.modules.user.FIND_BY_USERNAME_ACTIVE";
    public static final String LIST_SYSTEM_ADMIN ="np.com.drose.parkgarau.modules.user.LIST_SYSTEM_ADMIN";
    
    @Id
    @Column(name = "user_name")
    private String userName;

    @NotNull(message = "password cannot be empty")
    @Column(name = "users_password")
    private String userPassword;

    @Column(name = "role_id")
    private String roleId;

    @Embedded
    private UserProfile userProfile;

    @Embedded
    private AuditInfo auditInfo;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "is_active")
    private boolean active = true;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.userName);
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
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", userPassword=" + userPassword + ", roleId=" + roleId + ", userProfile=" + userProfile + ", auditInfo=" + auditInfo + ", userType=" + userType + ", active=" + active + '}';
    }

}
