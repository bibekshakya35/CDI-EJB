package np.com.drose.parkgarau.modules.grouppermission;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

import np.com.drose.parkgarau.modules.permission.Permission;

/**
 *
 * @author bibek
 */
//suppress compile warning of serialVersionUID need
@SuppressWarnings("serial")
@Entity
@NamedQueries(value = {
    @NamedQuery(name = GroupPermission.FIND_BY_CODE, query = "select p from GroupPermission p where p.code = :code"),
    @NamedQuery(name = GroupPermission.FIND_BY_ALL, query = "select p from GroupPermission p"),
    @NamedQuery(name = GroupPermission.FIND_BY_CODE_WITH_PERMISSIONS, query = "select p from GroupPermission p left join fetch p.permissions where p.code = :code")
})
@Table(name = "grouppermissions")
public class GroupPermission implements AbstractEntity, Serializable {

    public static final String FIND_BY_CODE = "np.com.drose.parkgarau.modules.grouppermission.FIND_BY_CODE";
    public static final String FIND_BY_ALL = "np.com.drose.parkgarau.modules.grouppermission.FIND_BY_ALL";
    public static final String FIND_BY_CODE_WITH_PERMISSIONS = "np.com.drose.parkgarau.modules.grouppermission.FIND_BY_CODE_WITH_PERMISSIONS";

    @Id
    @Column(name = "group_code", unique = true)
    private String code;

    private String description;

    @Column(name = "is_active")
    private boolean active = true;
    @Embedded
    private AuditInfo auditInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "group", orphanRemoval = true)
    private Set<Permission> permissions = new HashSet<>(0);

    
    @Column(name = "groupTypes")
    private String groupTypes;

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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermissions(Set<Permission> permissions) {
        this.permissions.addAll(permissions);
    }

    public void addPermission(Permission permission) {
        if (this.permissions.contains(permission)) {
            this.permissions.remove(permission);
        }
        permission.setActive(true);
        permission.setGroup(this);
        this.permissions.add(permission);
    }

    public void disablePermission(Permission permission) {
        if (this.permissions.contains(permission)) {
            this.permissions.remove(permission);
            permission.setActive(false);
            this.permissions.add(permission);
        }
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
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }

    public String getGroupTypes() {
        return groupTypes;
    }

    public void setGroupTypes(String groupTypes) {
        this.groupTypes = groupTypes;
    }

    @Override
    public String toString() {
        return "GroupPermission{" + "code=" + code + ", description=" + description + ", active=" + active + ", auditInfo=" + auditInfo + ", permissions=" + permissions + ", groupTypes=" + groupTypes + '}';
    }
    
}
