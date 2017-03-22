package np.com.drose.parkgarau.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.role.PermissionRole;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.pages.BaseEditBean;
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
public class RoleEditController extends BaseEditBean<Role> {
    
    @Inject
    private IdentityBean identityBean;
    private static final Logger LOG = Logger.getLogger(RoleEditController.class.getName());
    private String groupCode = "";
    private int counter = 0;
    
    @Inject
    ParkGarauService<Role> parkGarauService;
    @Inject
    ParkGarauService<Company> companyGarauService;
    
    List<Company> companies;
    
    private String companyCode;
    
    List<Permission> permissions;
    
    @Inject
    ParkGarauService<PermissionRole> pRParkGarauService;
    
    List<PermissionRole> permissionRoles;
    
    Map<String, Boolean> selectedIds;
    
    private Object selected;
    
    private boolean managed = false;
    
    @Inject
    ParkGarauService<Permission> permissionService;
    
    PermissionRole permissionRole;
    
    Map<String, List<Permission>> groupMap;
    
    List<Map.Entry<String, List<Permission>>> entries;
    
    List<GroupPermission> groupPermissions;
    
    @Inject
    ParkGarauService<GroupPermission> groupPermissionService;
    
    
    
    @PostConstruct
    @Override
    protected void init() {
        super.init();
        if (!identityBean.hasAccess("ROLE_UPDATE")) {
            this.identityBean.authorize();
        }
        //To change body of generated methods, choose Tools | Templates.
        permissions = this.permissionService.getList();
        this.selectedIds = new HashMap<>();
        groupMap = new TreeMap<>();
        groupPermissions = this.groupPermissionService.getList();
        
        if (this.groupMap.isEmpty()) {
            this.groupPermissions.stream().forEach((gp) -> {
                groupMap.put(gp.getCode(), new ArrayList<>(gp.getPermissions()));
            });
        }
        this.entries = new ArrayList<>(groupMap.entrySet());
        LOG.log(Level.INFO, "size of the permission list{0}", this.permissions.size());
        this.permissionRole = new PermissionRole();
        companies = this.companyGarauService.getList();
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
        identityBean.hasAccess("ROLE_UPDATE");
        if (this.selectedItem != null && this.selectedItem.toString().length() > 0) {
            LOG.log(Level.INFO, "-----id-----{0}", selectedItem.toString());
            this.instance = this.parkGarauService.finder(this.selectedItem.toString());
            this.loadPermissionByRole(this.instance);
            LOG.log(Level.INFO, "size of permision role{0}", permissionRoles.size());
            if (this.instance == null) {
                FacesUtils.addMessage("error", "Role", "NO RECORD FOUND");
                return;
            }
        }
        
    }
    
    public void loadSelectedItem(String perCode) {
        this.getPermissions();
        try {
            PermissionRole pr = new PermissionRole();
            pr.setPermissionCode(perCode);
            pr.setCompanyCode(companyCode);
            pr.setRoleCode(this.instance.getCode());
            if (this.permissionRoles.contains(pr)) {
                this.permissionRoles.remove(pr);
            } else {
                this.permissionRoles.add(pr);
            }
            
            LOG.log(Level.INFO, "size={0}", this.permissionRoles.size());
            this.permissionRoles.stream().forEach((r) -> {
                LOG.log(Level.INFO, "ppp={0}", r.getPermissionCode());
            });
        } catch (IllegalArgumentException ex) {
            FacesUtils.addMessage("messages", "ROle", ex.getMessage());
        }
    }
    
    private void loadPermissionByRole(Role r) {
        try {
            this.permissionRoles = this.pRParkGarauService.findList(r.getCode());
            LOG.log(Level.INFO, "size{0}", permissionRoles.size());
            this.permissionRoles.stream().forEach((p) -> {
                this.selectedIds.put(p.getPermissionCode(), Boolean.TRUE);
            });
        } catch (IllegalArgumentException e) {
            FacesUtils.addMessage("messages", "Role", e.getMessage());
        }
    }
    
    public List<Permission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    public List<PermissionRole> getPermissionRoles() {
        return permissionRoles;
    }
    
    public void setPermissionRoles(List<PermissionRole> permissionRoles) {
        this.permissionRoles = permissionRoles;
    }
    
    public String getGroupCode(Permission p) {
        if (groupCode.equals(p.getGroupPermission().getCode())) {
            this.counter = counter + 1;
        }
        if (!groupCode.equals(p.getGroupPermission().getCode())) {
            counter = 1;
            this.groupCode = p.getGroupPermission().getCode();
            
            return "<div class='clear'></div></br>" + this.groupCode + "</br>";
            
        }
        if (counter % 6 == 0) {
            return "<div class='clear'></div>";
        }
        
        return "";
    }
    
    public String getGroupCode() {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
    public int getCounter() {
        return counter;
    }
    
    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    public Map<String, Boolean> getSelectedIds() {
        return selectedIds;
    }
    
    public void setSelectedIds(Map<String, Boolean> selectedIds) {
        this.selectedIds = selectedIds;
    }
    
    public Object getSelected() {
        return selected;
    }
    
    public void setSelected(Object selected) {
        this.selected = selected;
    }
    
    public boolean isManaged() {
        return managed;
    }
    
    public void setManaged(boolean managed) {
        this.managed = managed;
    }
    
    @Override
    public String onSave() {
        try {
            LOG.log(Level.INFO, "Permission size{0}", this.permissionRoles.size());
            this.parkGarauService.edit(this.instance);
            this.pRParkGarauService.delete(this.instance.getCode());
            this.pRParkGarauService.editAll(permissionRoles);
            FacesUtils.addMessage("messages", "Role", "edit sucessful");
            return "pretty:role_list";
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "Role", e.getMessage());
            return "pretty:role_add";
        }
    }
    
    public Map<String, List<Permission>> getGroupMap() {
        return groupMap;
    }
    
    public void setGroupMap(Map<String, List<Permission>> groupMap) {
        this.groupMap = groupMap;
    }
    
    public List<Map.Entry<String, List<Permission>>> getEntries() {
        return entries;
    }
    
    public void setEntries(List<Map.Entry<String, List<Permission>>> entries) {
        this.entries = entries;
    }
    
    public List<GroupPermission> getGroupPermissions() {
        return groupPermissions;
    }
    
    public void setGroupPermissions(List<GroupPermission> groupPermissions) {
        this.groupPermissions = groupPermissions;
    }
    
    public List<Company> getCompanies() {
        return companies;
    }
    
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    public String getCompanyCode() {
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
}
