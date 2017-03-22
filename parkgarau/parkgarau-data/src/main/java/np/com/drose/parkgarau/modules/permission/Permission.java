/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.permission;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;

/**
 *
 * @author bibek
 */
@SuppressWarnings("serials")
@Entity
@Table(name = "permissions")
@NamedQueries(value = {
    @NamedQuery(name = Permission.FIND_ALL, query = "select p from Permission p"),
    @NamedQuery(name = Permission.FIND_BY_CODE, query = "select p from Permission p where p.code=:code")
})
public class Permission implements AbstractEntity, Serializable {

    public static final String FIND_ALL = "np.com.drose.parkgarau.modules.permission.FIND_ALL";

    public static final String FIND_BY_CODE = "np.com.drose.parkgarau.modules.permission.FIND_BY_CODE";

    @Id
    private String code;

    @Column(name = "permission_description", nullable = false)
    private String description;

    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_code", nullable = false)
    private GroupPermission group;
    
    @Transient
    private String groupCode;

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

    public GroupPermission getGroupPermission() {
        return group;
    }

    public void setGroupPermission(GroupPermission groupPermission) {
        this.group = groupPermission;
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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + Objects.hashCode(this.group);
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
        final Permission other = (Permission) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        return true;
    }

    public GroupPermission getGroup() {
        return group;
    }

    public void setGroup(GroupPermission group) {
        this.group = group;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    

    public Permission() {
    }

    public Permission(String code, String description, GroupPermission group) {
        this.code = code;
        this.description = description;
        this.group = group;
    }
    
}
