package np.com.drose.parkgarau.grouppermission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.pages.BaseEditBean;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@ViewScoped
public class GroupPermissionEditController extends BaseEditBean<GroupPermission> implements Serializable {

    private static final Logger LOG = Logger.getLogger(GroupPermissionEditController.class.getName());

    @Inject
    ParkGarauService<GroupPermission> groupPermissionService;

    @Inject
    ParkGarauService<Permission> permissionService;

    @Inject
    transient IdentityBean identityBean;

    Set<Permission> permissions = new HashSet<>();

    private Permission permission;
    private Permission selectedPermission;
    String[] userType;

    public UserType[] getUserTypes() {
        return UserType.values();
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        if (!identityBean.hasAccess("ROLE_UPDATE")) {
            this.identityBean.authorize();
        }
        this.permission = new Permission();

    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    private String editData() {
        StringBuilder types = new StringBuilder();
        if (userType.length == 2) {
            types.append(userType[0]).append(",").append(userType[1]);
        } else {
            types.append(userType[0]);
        }
        this.instance.setAuditInfo(new AuditInfo());
        this.instance.setGroupTypes(types.toString());
        this.instance.addPermissions(permissions);
        this.groupPermissionService.edit(this.instance);
        FacesUtils.addMessage("messages", "Group Permission", "Record - " + this.instance.getCode() + " has been edited in databases");
        return "pretty:group_list";
    }

    public void removeData() {
        LOG.log(Level.INFO, "selected permission = {0}{1}", new Object[]{this.selectedPermission.getCode(), this.selectedPermission.getGroupPermission().getCode()});
        this.selectedPermission.setActive(false);
        this.instance.disablePermission(this.selectedPermission);
        this.permissions.remove(this.selectedPermission);
//this.loadPermissions();
    }

    public void removeAll() {
        this.permissions.clear();
    }

    public void addList() {
        this.permission.setGroupPermission(this.instance);
        this.instance.addPermission(permission);
        //this.loadPermissions();
        this.permissions.add(permission);
        this.permission = new Permission();
    }

    @Override
    public String onSave() {
        return this.editData();
    }

    @Override
    public String onDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String onChangeActiveStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public void setSelectedItem(Object item) {
        this.selectedItem = item;
    }

    @Override
    public void loadSelectedItem() {
        if (this.instance != null) {
            return;
        }
        if (selectedItem != null && selectedItem.toString().length() > 0) {
            System.out.println("Selected Code = " + selectedItem);
            this.instance = this.groupPermissionService.findWithAnotherObjectCode(selectedItem.toString());
            if (this.instance == null) {
                FacesUtils.addMessage("messages", "Group Permission", "Unable to find the record");
                return;
            }
            this.loadPermissions();
        }
    }

    private void loadPermissions() {
        for (Permission p : this.instance.getPermissions()) {
            if (p.isActive()) {
                this.permissions.add(p);
            }
        }
        LOG.log(Level.INFO, "Count permissions1 = {0}", this.instance.getPermissions().size());
        LOG.log(Level.INFO, "Count permissions2 = {0}", this.permissions.size());
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getSelectedPermission() {
        return selectedPermission;
    }

    public void setSelectedPermission(Permission selectedPermission) {
        this.selectedPermission = selectedPermission;
    }

    public String[] getUserType() {
        return userType;
    }

    public void setUserType(String[] userType) {
        this.userType = userType;
    }

}
