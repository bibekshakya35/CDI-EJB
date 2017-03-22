/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.users;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.role.Role;

import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.modules.userprofile.UserProfile;
import np.com.drose.parkgarau.pages.BaseAddBean;
import np.com.drose.parkgarau.platform.security.PasswordEncryptor;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@RequestScoped
public class UserAddController extends BaseAddBean<User> {

    String roleCode;
    AuditInfo auditInfo;

    private String confirmpassword;

    UserProfile userProfile;

    @Inject
    ParkGarauService<User> userServices;

    private static final Logger LOG = Logger.getLogger(UserAddController.class.getName());

    @Inject
    ParkGarauService<Role> roleGarauService;

    List<Role> roles;

    @Inject
    transient IdentityBean identityBean;

    public UserType[] getTypes() {
        return UserType.values();
    }

    @PostConstruct
    @Override
    protected void init() {
        if (!identityBean.hasAccess("USER_UPDATE")) {
            this.identityBean.authorize();
        }
        this.instance = new User();
        this.instance.setAuditInfo(new AuditInfo());
        roles = this.roleGarauService.getList();
        userProfile = new UserProfile();
        auditInfo = new AuditInfo();
    }

    @Override
    public String onSave() {
        try {
            LOG.info("-------------inside the save------------");
            if (!this.instance.getUserPassword().equals(confirmpassword)) {
                FacesUtils.addMessage("messages", "Sorry password didnot match");
                return "pretty:user_add";
            }
            this.instance.setUserPassword(PasswordEncryptor.encode(this.instance.getUserPassword()));
            this.instance.setUserProfile(userProfile);
            this.instance.setAuditInfo(auditInfo);
            this.instance.setRoleId(roleCode);
            this.userServices.add(this.instance);
            FacesUtils.addMessage("messages", "User", "User " + this.instance.getUserProfile().getFullname() + " has been added");
            return "pretty:user_list";
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "User", "error " + e.getMessage());
            return "pretty:user_add";
        }
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
