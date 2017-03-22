/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.role;

import java.util.ArrayList;
import java.util.Collections;
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
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.role.PermissionRole;

import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.pages.BaseAddBean;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@ViewScoped
public class RoleAddController extends BaseAddBean<Role> {
    
    private static final Logger LOG = Logger.getLogger(RoleAddController.class.getName());
    
    private String groupCode = "";
    private int counter = 0;
    @Inject
    ParkGarauService<Permission> permissionParkGarauService;
    
    private String companyCode;
    
    Map<String, List<Permission>> groupMap;
    
    List<Map.Entry<String, List<Permission>>> entries;
    
    @Inject
    ParkGarauService<Role> roleParkGarauService;
    
    @Inject
    ParkGarauService<PermissionRole> permissionRoleParkGarauService;
    
    @Inject
    ParkGarauService<GroupPermission> groupPermissionService;
    
    @Inject
    ParkGarauService<Company> companyGarauService;
    
    Map<String, Boolean> selectedIds;
    
    List<Company> companies;
    
    List<Permission> permissions;
    
    List<GroupPermission> groupPermissions;
    
    List<PermissionRole> permissionRoles;
    
    @Inject
    transient IdentityBean identityBean;
    
    @PostConstruct
    @Override
    protected void init() {
        super.init();
        if (!identityBean.hasAccess("ROLE_ADD")) {
            this.identityBean.authorize();
        }
        this.instance = new Role();
        this.selectedIds = new HashMap<>();
        permissionRoles = new ArrayList<>();
        groupMap = new TreeMap<>();
        groupPermissions = this.groupPermissionService.getList();
        if (this.groupMap.isEmpty()) {
            
            this.groupPermissions.stream().forEach((GroupPermission gp) -> {
                ArrayList<Permission> permissionList = new ArrayList<>(gp.getPermissions());
                permissionList.sort((a1,b1)->a1.getCode().compareTo(b1.getCode()));
                groupMap.put(gp.getCode(), permissionList);
            });
        }
        this.entries = new ArrayList<>(groupMap.entrySet());
        this.companies = companyGarauService.getList();
//To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Permission> getPermissions() {
        return this.permissionParkGarauService.getList();
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
    
    @Override
    public String onSave() {
        try {
            LOG.log(Level.INFO, "Permission size{0}", this.permissionRoles.size());
            this.instance.setAuditInfo(new AuditInfo());
            this.roleParkGarauService.add(this.instance);
            this.permissionRoleParkGarauService.addAll(permissionRoles);
            FacesUtils.addMessage("messages", "Role", "ADD sucessful");
            return "pretty:role_list";
        } catch (Exception e) {
            FacesUtils.addMessage("messages", e.getMessage());
            return "pretty:role_add";
        }
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
    
    public void loadSelectedItem(String perCode) {
        this.getPermissions();
        try {
            PermissionRole permissionRole = new PermissionRole();
            permissionRole.setPermissionCode(perCode);
            if (companyCode == null) {
                companyCode = "DROSE";
            }
            permissionRole.setCompanyCode(companyCode);
            permissionRole.setRoleCode(this.instance.getCode());
            if (this.permissionRoles.contains(permissionRole)) {
                this.permissionRoles.remove(permissionRole);
            } else {
                this.permissionRoles.add(permissionRole);
            }
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "Role", "Error");
        }
    }
    
    public Map<String, Boolean> getSelectedIds() {
        return selectedIds;
    }
    
    public void setSelectedIds(Map<String, Boolean> selectedIds) {
        this.selectedIds = selectedIds;
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
    
    public String getCompanyCode() {
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    public List<Company> getCompanies() {
        return companies;
    }
    
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
}
