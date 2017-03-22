/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.pages.BaseEditBean;
import np.com.drose.parkgarau.platform.security.PasswordEncryptor;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author bibek Edit the User and Userprofile of system
 */
@Named
@SessionScoped
public class UserEditController extends BaseEditBean<User> implements Serializable {

    private String roleCode;

    private String confirmpassword;

    @Inject
    ParkGarauService<User> userServices;

    private static final Logger LOG = Logger.getLogger(UserAddController.class.getName());

    @Inject
    ParkGarauService<Role> roleGarauService;

    List<Role> roles;

    public UserType[] getTypes() {
        return UserType.values();
    }

    @Inject
    transient IdentityBean identityBean;

    @Override
    public String onSave() {
        try {
            LOG.info("---EDIT user account---");
            
            if (!this.instance.getUserPassword().equals(confirmpassword)) {
                FacesUtils.addMessage("messages", "Sorry password didnot match");
                return "pretty:user_add";
            }
            this.instance.setRoleId(roleCode);
            this.instance.setUserPassword(PasswordEncryptor.encode(confirmpassword));
            this.instance.getAuditInfo().setModifiedOn(new Date());
            this.instance.getAuditInfo().setVerifiedOn(null);
            this.userServices.edit(this.instance);
            FacesUtils.addMessage("messages", "User", "user " + this.instance.getUserProfile().getFullname() + " has been modified");
            return "pretty:user_list";
        } catch (Exception e) {
            FacesUtils.addMessage("errorMessages", "user", "error " + e.getMessage());
            return "pretty:user_edit";
        }
    }

    @PostConstruct
    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if (!identityBean.hasAccess("USER_UPDATE")) {
            this.identityBean.authorize();
        }
        roles = this.roleGarauService.getList();
        
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
        return selectedItem;
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
        if (this.selectedItem != null && StringUtils.isNotBlank(this.selectedItem.toString())) {
            LOG.log(Level.INFO, "selected transaction {0}", this.selectedItem.toString());
            this.instance = userServices.finder(this.selectedItem.toString());

            if (this.instance == null) {
                FacesUtils.addMessage("errorMessages", "User", "User already exists");
                return;
            }
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

}
