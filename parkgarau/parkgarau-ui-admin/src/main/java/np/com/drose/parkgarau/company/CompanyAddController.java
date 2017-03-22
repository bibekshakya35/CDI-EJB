/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.company;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.AuditInfo;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.company.CompanyAdmin;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.modules.user.UserType;
import np.com.drose.parkgarau.modules.userprofile.UserProfile;
import np.com.drose.parkgarau.pages.BaseAddBean;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;
import org.primefaces.context.RequestContext;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@ViewScoped
public class CompanyAddController extends BaseAddBean<Company> implements Serializable {

    private String roleCode;

    private static final Logger LOG = Logger.getLogger(CompanyAddController.class.getName());

    CompanyAdmin companyAdmin;

    User user;

    UserProfile userProfile;

    List<Role> roles;

    Set<User> users = new HashSet<>();

    @Inject
    ParkGarauService<Role> roleParkGarauService;

    @Inject
    ParkGarauService<User> userParkGarauService;

    @Inject
    ParkGarauService<Company> companyParkGarauService;
    AuditInfo auditInfo;
    @Inject
    ParkGarauService<CompanyAdmin> companyAdminParkGarauService;
    Set<String> userNames;
    private String confirmPassword;

    @Inject
    transient IdentityBean identityBean;

    @Override
    @PostConstruct
    protected void init() {
        if (!identityBean.hasAccess("COMPANY_ADD")) {
            identityBean.authorize();
        }
        userNames = new HashSet<>();
        companyAdmin = new CompanyAdmin();
        this.instance = new Company();
        user = new User();

        auditInfo = new AuditInfo();
        userProfile = new UserProfile();
        roles = this.roleParkGarauService.getList();

    }

    @Override
    public String onSave() {
        try {
            //first step user add
            if (!users.isEmpty()) {
                userParkGarauService.addAll(new ArrayList<>(users));

            }
            this.instance.setAuditInfo(auditInfo);
            LOG.info("---List of user has been added-----");
            companyParkGarauService.add(this.instance);
            if (!this.userNames.isEmpty()) {
                companyAdmin.setCompanyCode(this.instance.getCode());
                companyAdmin.setUserName(new ArrayList<>(userNames));
                companyAdminParkGarauService.add(companyAdmin);
            }
            LOG.info("--Company has been added---");
            FacesUtils.addMessage("messages", "Company", "Compnay " + this.instance + " has been added in database");
        } catch (Exception e) {
            FacesUtils.addMessage("errorMessages", "company", "unable to save" + e.getMessage());
            return "pretty:company_add";
        }
        return "pretty:company_list";
    }

    public UserType[] getTypes() {
        return UserType.values();
    }

    public void remove(User user) {
        users.remove(user);
    }

    public void addList() {
        user.setUserProfile(userProfile);
        user.setRoleId(roleCode);
        user.setAuditInfo(new AuditInfo());
        boolean isEmailExists = userNames.add(user.getUserName());
        boolean isTrue = users.add(user);
        if (!isEmailExists) {
            FacesUtils.addMessage("messages", "email id already exists, so use unique one");
            users.remove(user);
        }
        if (!isTrue) {
            FacesUtils.addMessage("messages", "User Already exists");
        }
        user = new User();
        userProfile = new UserProfile();
        confirmPassword = "";
        LOG.log(Level.INFO, "size of users{0}", users.size());
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void reset() {
        RequestContext.getCurrentInstance().reset(":addUserForm");
    }

}
