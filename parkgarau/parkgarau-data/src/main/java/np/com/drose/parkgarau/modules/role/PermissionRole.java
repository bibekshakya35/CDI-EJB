/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.modules.role;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import np.com.drose.parkgarau.modules.AbstractEntity;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "permissions_roles")
@NamedQueries(value = {
    @NamedQuery(name = PermissionRole.FIND_BY_ROLECODE,query = "select p from PermissionRole p where p.roleCode =:code"),
    @NamedQuery(name = PermissionRole.FIND_BY_ALL,query = "select p from PermissionRole p"),
    @NamedQuery(name = PermissionRole.FIND_BY_PRID,query = "SELECT p FROM PermissionRole p where p.id =:id"),
    @NamedQuery(name = PermissionRole.DELETE_BY_ROLEID,query = "DELETE FROM PermissionRole p WHERE p.roleCode=:code")
})
public class PermissionRole implements Serializable,AbstractEntity{
    
    public static final String FIND_BY_ROLECODE = "np.com.drose.parkgarau.modules.role.FIND_BY_ROLECODE";
    public static final String FIND_BY_ALL = "np.com.drose.parkgarau.modules.role.FIND_BY_ALL";
    public static final String FIND_BY_PRID = "np.com.drose.parkgarau.modules.role.FIND_BY_PRID";
    public static final String DELETE_BY_ROLEID="np.com.drose.parkgarau.modules.role.DELETE_BY_ROLEID";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    
    private String roleCode;
    
    private String permissionCode;
    
    private String companyCode;

    @Column(name = "is_active")
    private boolean  active= true;
    
    public PermissionRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.roleCode);
        hash = 83 * hash + Objects.hashCode(this.permissionCode);
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
        final PermissionRole other = (PermissionRole) obj;
        if (!Objects.equals(this.roleCode, other.roleCode)) {
            return false;
        }
        if (!Objects.equals(this.permissionCode, other.permissionCode)) {
            return false;
        }
        return true;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public PermissionRole(String roleCode, String permissionCode) {
        this.roleCode = roleCode;
        this.permissionCode = permissionCode;
    }
    
    
    
}
