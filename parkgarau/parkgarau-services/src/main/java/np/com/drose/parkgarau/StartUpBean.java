/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.menu.Menu;
import np.com.drose.parkgarau.menu.group.MenuGroup;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.role.PermissionRole;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.modules.userprofile.UserProfile;
import np.com.drose.parkgarau.platform.security.PasswordEncryptor;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class StartUpBean {

    private static final Logger LOG = Logger.getLogger(StartUpBean.class.getName());

    @Inject
    ParkGarauService<GroupPermission> groupPermissionParkGarauService;

    @Inject
    ParkGarauService<Role> roleParkGarauService;

    @Inject
    ParkGarauService<PermissionRole> permissionRoleParkGarauService;

    @Inject
    ParkGarauService<User> userParkGarauService;

    @Inject
    ParkGarauService<Permission> permissionParkGarauService;

    @Inject
    ParkGarauService<Menu> menuGarauService;
    @Inject
    ParkGarauService<MenuGroup> menuGroupGarauService;

    @PostConstruct
    public void init() {
        this.addDefaultGroupPermission();
        this.addDefaultSystemAdmin();
        this.addDefaultCompanyAdmin();
        this.addDefaultAdminUser();
        this.addDefaultMenuPermission();
    }

    private void addDefaultAdminUser() {
        if (userParkGarauService.finder("admin") != null) {
            return;
        }
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword(PasswordEncryptor.encode("12345"));
        user.setRoleId("SYS_ADMIN");
        user.setUserType(UserType.SYSTEM);
        user.setAuditInfo(new AuditInfo());
        UserProfile up = new UserProfile();
        up.setFullname("Bibek Shakya");
        up.setEmailId("bibek@drose.com.np");
        up.setLandLineNumber("014323565");
        up.setMobileNumber("9843598726");
        up.setBasicInfo("hero ho hero ");
        user.setUserProfile(up);
        userParkGarauService.add(user);
    }

    private void addDefaultMenuPermission() {
        if (menuGarauService.finder(4) != null) {
            return;
        } else {
            Menu menu = new Menu();
            menu.setText("Admin");
            menu.setIconCls("fa fa-user");
            menu.setLeaf(Boolean.FALSE);
            menuGarauService.add(menu);
            menuGarauService.add(new Menu("Group", "fa fa-users", 1, "panel", Boolean.TRUE));
            menuGarauService.add(new Menu("User", "fa fa-user-circle-o", 1, "users", Boolean.TRUE));
            menuGarauService.add(new Menu("Vehicle", "fa fa-motorcycle", 1, "panel", Boolean.TRUE));
            if (menuGroupGarauService.finder(3) != null) {
                return;
            } else {
                menuGroupGarauService.add(new MenuGroup(1, "SYS_ADMIN"));
                menuGroupGarauService.add(new MenuGroup(2, "SYS_ADMIN"));
                menuGroupGarauService.add(new MenuGroup(3, "SYS_ADMIN"));
            }
        }
    }

    private void addDefaultGroupPermission() {
        if (this.permissionParkGarauService.finder("USER_ADD") != null) {
            return;

        } else {
            String[][] permissions = {{"USER_ADD", "USER_ADD", "USER"},
            {"USER_UPDATE", "USER_UPDATE", "USER"},
            {"USER_DELETE", "USER_DELETE", "USER"},
            {"USER_VERIFY", "USER_VERIFY", "USER"},
            {"USER_VIEW", "USER_VIEW", "USER"},
            {"ROLE_ADD", "ROLE_ADD", "ROLE"},
            {"ROLE_UPDATE", "ROLE_UPDATE", "ROLE"},
            {"ROLE_DELETE", "ROLE_UPDATE", "ROLE"},
            {"ROLE_VIEW", "ROLE_VIEW", "ROLE"},
            {"ROLE_VERIFY", "ROLE_VERIFY", "ROLE"},
            {"COMPANY_ADD", "COMPANY_ADD", "COMPANY"},
            {"COMPANY_UPDATE", "COMPANY_UPDATE", "COMPANY"}, {"COMPANY_DELETE", "COMPANY_DELETE", "COMPANY"}, {"COMPANY_VERIFY", "COMPANY_VERIFY", "COMPANY"}, {"COMPANY_VIEW", "COMPANY_VIEW", "COMPANY"},
            {"COMPANY_BRANCH_ADD", "COMPANY_BRANCH_ADD", "COMPANY_BRANCH"}, {"COMPANY_BRANCH_UPDATE", "COMPANY_BRANCH_UPDATE", "COMPANY_BRANCH"}, {"COMPANY_BRANCH_DELETE", "COMPANY_BRANCH_DELETE", "COMPANY_BRANCH"}, {"COMPANY_BRANCH_VERIFY", "COMPANY_BRANCH_VERIFY", "COMPANY_BRANCH"}, {"COMPANY_BRANCH_VIEW", "COMPANY_BRANCH_VIEW", "COMPANY_BRANCH"},
            {"DEVICE_ADD", "DEVICE_ADD", "DEVICE",}, {"DEVICE_UPDATE", "DEVICE_UPDATE", "DEVICE"}, {"DEVICE_DELETE", "DEVICE_DELETE", "DEVICE"}, {"DEVICE_VERIFY", "DEVICE_VERIFY", "DEVICE"}, {"DEVICE_VIEW", "DEVICE_VIEW", "DEVICE"},
            {"VEHICLE_CATEGORY_ADD", "VEHICLE_CATEGORY_ADD", "VEHICLE_CATEGORY"}, {"VEHICLE_CATEGORY_UPDATE", "VEHICLE_CATEGORY_UPDATE", "VEHICLE_CATEGORY"}, {"VEHICLE_CATEGORY_DELETE", "VEHICLE_CATEGORY_DELETE", "VEHICLE_CATEGORY"}, {"VEHICLE_CATEGORY_VERIFY", "VEHICLE_CATEGORY_VERIFY", "VEHICLE_CATEGORY"}, {"VEHICLE_CATEGORY_VIEW", "VEHICLE_CATEGORY_VIEW", "VEHICLE_CATEGORY"},
            {"VEHICLE_SUBCATEGORY_ADD", "VEHICLE_SUBCATEGORY_ADD", "VEHICLE_SUBCATEGORY",}, {"VEHICLE_SUBCATEGORY_UPDATE", "VEHICLE_SUBCATEGORY_UPDATE", "VEHICLE_SUBCATEGORY"}, {"VEHICLE_SUBCATEGORY_DELETE", "VEHICLE_SUBCATEGORY_DELETE", "VEHICLE_SUBCATEGORY",}, {"VEHICLE_SUBCATEGORY_VERIFY", "VEHICLE_SUBCATEGORY_VERFIRY", "VEHICLE_SUBCATEGORY"}, {"VEHICLE_SUBCATEGORY_VIEW", "VEHICLE_SUBCATEGORY_VIEW", "VEHICLE_SUBCATEGORY"},
            {"PARK_CHARGE_SETUP_ADD", "PARK_CHARGE_SETUP_ADD", "PARK_CHARGE_SETUP"}, {"PARK_CHARGE_SETUP_UPDATE", "PARK_CHARGE_SETUP_UPDATE", "PARK_CHARGE_SETUP"}, {"PARK_CHARGE_SETUP_DELETE", "PARK_CHARGE_SETUP_DELETE", "PARK_CHARGE_SETUP"}, {"PARK_CHARGE_SETUP_VERIFY", "PARK_CHARGE_SETUP_VERIFY", "PARK_CHARGE_SETUP"}, {"PARK_CHARGE_SETUP_VIEW", "PARK_CHARGE_SETUP_VIEW", "PARK_CHARGE_SETUP"},
            {"PARK_CHARGE_ADD", "PARK_CHARGE_ADD", "PARK_CHARGE"}, {"PARK_CHARGE_UPDATE", "PARK_CHARGE_UPDATE", "PARK_CHARGE"}, {"PARK_CHARGE_DELETE", "PARK_CHARGE_DELETE", "PARK_CHARGE"}, {"PARK_CHARGE_VERIFY", "PARK_CHARGE_VERIFY", "PARK_CHARGE"}, {"PARK_CHARGE_VIEW", "PARK_CHARGE_VIEW", "PARK_CHARGE"}};
            String[][] groups = {{"USER", "USER DETAIL", "SYSTEM,COMPANY"},
            {"ROLE", "ROLE DETAIL", "SYSTEM,COMPANY"},
            {"COMPANY", "COMPANY DETAIL", "SYSTEM"},
            {"COMPANY_BRANCH", "COMPANY_BRANCH DETAIL", "COMPANY"},
            {"DEVICE", "DEVICE DETAIL", "COMPANY"},
            {"VEHICLE_CATEGORY", "VEHICLE_CATEGORY DETAIL", "SYSTEM"},
            {"VEHICLE_SUBCATEGORY", "VEHICLE_CATEGORY DETAIL", "SYSTEM"},
            {"PARK_CHARGE_SETUP", "PARK_CHARGE_SETUP DETAIL", "COMPANY"},
            {"PARK_CHARGE", "PARK_CHARGE DETAIL", "COMPANY"}};

            Set<Permission> permissionSet = new HashSet<>();
            for (String[] group : groups) {
                GroupPermission groupPermission = new GroupPermission();
                groupPermission.setCode(group[0]);
                groupPermission.setDescription(group[1]);
                groupPermission.setGroupTypes(group[2]);
                for (String[] per : permissions) {
                    if (group[0].equals(per[2])) {
                        permissionSet.add(new Permission(per[0], per[1], groupPermission));
                    }
                }
                groupPermission.addPermissions(permissionSet);
                groupPermission.setAuditInfo(new AuditInfo());
                groupPermissionParkGarauService.add(groupPermission);

            }
            LOG.log(Level.INFO, "size of group list >>>>>>>>>>>>>>>>>>>>>>>>>>>>{0}", groupPermissionParkGarauService.getList());
        }
    }

    private void addDefaultSystemAdmin() {
        if (this.roleParkGarauService.finder("SYS_ADMIN") != null) {
            return;
        }
        List<PermissionRole> permissionRoles = new ArrayList<>();
        Role role = new Role();
        role.setCode("SYS_ADMIN");
        role.setDescription("System Admin");
        role.setAuditInfo(new AuditInfo());

        List<GroupPermission> listGroup = groupPermissionParkGarauService.getList();
        listGroup.stream().forEach((g) -> {
            if (g.getGroupTypes().equals("SYSTEM") || g.getGroupTypes().equals("SYSTEM,COMPANY")) {
                g.getPermissions().stream().forEach((p) -> {
                    permissionRoles.add(new PermissionRole(role.getCode(), p.getCode()));
                });
            }
        });
        LOG.log(Level.INFO, "size of the permissionrole{0}", permissionRoles.size());
        roleParkGarauService.add(role);
        permissionRoleParkGarauService.addAll(permissionRoles);
    }

    private void addDefaultCompanyAdmin() {
        if (this.roleParkGarauService.finder("COMPANY_ADMIN") != null) {
            return;
        }
        List<PermissionRole> permissionRoles = new ArrayList<>();
        Role role = new Role();
        role.setCode("COMPANY_ADMIN");
        role.setDescription("COMPANY ADMIN");
        role.setAuditInfo(new AuditInfo());
        List<GroupPermission> listGroup = groupPermissionParkGarauService.getList();
        listGroup.stream().forEach((g) -> {
            if (g.getGroupTypes().equals("COMPANY") || g.getGroupTypes().equals("SYSTEM,COMPANY")) {
                g.getPermissions().stream().forEach((p) -> {
                    permissionRoles.add(new PermissionRole(role.getCode(), p.getCode()));

                });
            }
        });
        roleParkGarauService.add(role);
        permissionRoleParkGarauService.addAll(permissionRoles);
    }

}
