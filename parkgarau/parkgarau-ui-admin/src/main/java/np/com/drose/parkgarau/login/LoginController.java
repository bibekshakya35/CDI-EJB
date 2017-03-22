/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.role.PermissionRole;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.HTTPUtils;
import np.com.drose.parkgarau.security.Identity;
import np.com.drose.parkgarau.security.ParkGarauCredential;
import np.com.drose.parkgarau.service.user.control.UserVerification;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class LoginController implements Serializable {
    
    private boolean showPage = true;
    private boolean companyPage = false;
    private ParkGarauCredential parkGarauCredential;
    @Inject
    UserVerification userVerification;
    
    @Inject
    ParkGarauService<User> parkGarauService;
    @Inject
    ParkGarauService<PermissionRole> permissionRoleParkGarauService;
    @Inject
    ParkGarauService<Permission> permissionParkGarauService;
    
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    @PostConstruct
    private void init() {
        this.parkGarauCredential = new ParkGarauCredential();
    }
    
    public String doLogin() {
        LOG.info("-------------------------------inside login form----------------------");
        try {
            if (this.parkGarauCredential.getUserName() == null || this.parkGarauCredential.getPassword() == null) {
                FacesUtils.addMessage("messages", "Login", "Please fill up the username and password");
                
                return null;
            }

            //user
            User validatedUser = this.processLogin(this.parkGarauCredential);
            LOG.log(Level.INFO, "--------------------------user{0}", validatedUser.toString());
            //role id anusar 
            List<PermissionRole> permissionRoles = this.permissionRoleParkGarauService.findList(validatedUser.getRoleId());
            //permission
            List<Permission> permissions = new ArrayList<>();
            //companycode
            String companyCode = permissionRoles.get(0).getCompanyCode();
            permissionRoles.stream().forEach((pr) -> {
                permissions.add(permissionParkGarauService.finder(pr.getPermissionCode()));
                LOG.log(Level.INFO, "permission object>>>>>>>>{0}", permissionParkGarauService.finder(pr.getPermissionCode()).getCode());
            });
            
            HTTPUtils.createHttpSession(new Identity(validatedUser.getUserName(), validatedUser.getUserProfile().getFullname(), companyCode, companyCode, validatedUser.getUserType(), permissions, true));
            LOG.info("inside the login");
            FacesUtils.addMessage("messages", "Successful Login , Welcome " + HTTPUtils.getSessionIdentity().getUserFullName());
            if (validatedUser.getUserType().equals(UserType.COMPANY)) {
                setShowPage(Boolean.FALSE);
                setCompanyPage(Boolean.TRUE);
            } else {
                setCompanyPage(false);
            }
            return "pretty:home";
            
        } catch (Exception e) {
            FacesUtils.addMessage("messages", "Invalid username and password");
            LOG.log(Level.INFO, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CAUSE OF THE ERROR{0}", e.getMessage());
            return null;
        }
    }
    
    private User processLogin(ParkGarauCredential user) {
        return this.userVerification.validateUser(user.getUserName(), user.getPassword());
    }
    
    public ParkGarauCredential getParkGarauCredential() {
        return parkGarauCredential;
    }
    
    public void setParkGarauCredential(ParkGarauCredential parkGarauCredential) {
        this.parkGarauCredential = parkGarauCredential;
    }
    
    public boolean isShowPage() {
        return showPage;
    }
    
    public void setShowPage(boolean showPage) {
        this.showPage = showPage;
    }
    
    public boolean isCompanyPage() {
        return companyPage;
    }
    
    public void setCompanyPage(boolean companyPage) {
        this.companyPage = companyPage;
    }
    
}
