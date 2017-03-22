package np.com.drose.parkgarau.grouppermission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.pages.BaseAddBean;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@ViewScoped
public class GroupPermissionAddController extends BaseAddBean<GroupPermission> implements Serializable {

    @Inject
    ParkGarauService<GroupPermission> groupPermissionService;

    //GroupPermission groupPermission;
    private Set<Permission> permissions = new HashSet<>();
    private Permission permission;
    String[] userType;

    @Inject
    transient IdentityBean identityBean;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        if (!identityBean.hasAccess("ROLE_ADD")) {
            this.identityBean.authorize();
        }
        this.instance = new GroupPermission();
        this.permission = new Permission();

        //groupPermission = new GroupPermission();
    }

    public UserType[] getUserTypes() {
        return UserType.values();
    }

    @Override
    public String onSave() {
        return this.addData();
    }

    /*
    public GroupPermission getGroupPermission() {
        return groupPermission;
    }

    public void setGroupPermission(GroupPermission groupPermission) {
        this.groupPermission = groupPermission;
    }
     */
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void removeData(Permission permission) {
        this.permissions.remove(permission);
    }

    public void addList() {
        this.permission.setGroupPermission(this.instance);
        permissions.add(this.permission);
        this.permission = new Permission();
    }

    private String addData() {
        try {
            StringBuilder types = new StringBuilder();
            this.instance.addPermissions(permissions);
//        this.instance.getAuditInfo().setFunctionCode(AuditInfo.FunctionCode.ADD);
            if (userType.length == 2) {
                types.append(userType[0]).append(",").append(userType[1]);
            } else {
                types.append(userType[0]);
            }
            this.instance.setAuditInfo(new AuditInfo());
            this.instance.setGroupTypes(types.toString());
            groupPermissionService.add(this.instance);
            FacesUtils.addMessage("messages", "Group Permission", "Record - " + this.instance.getCode() + " has been added in databases");
            return "pretty:group_list";
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "Error " + e.getMessage());
            return "pretty:group_add";
        }
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String[] getUserType() {
        return userType;
    }

    public void setUserType(String[] userType) {
        this.userType = userType;
    }

}
