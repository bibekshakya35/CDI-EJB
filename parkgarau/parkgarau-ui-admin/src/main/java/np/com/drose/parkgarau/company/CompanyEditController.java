/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.company;

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
import np.com.drose.parkgarau.pages.BaseEditBean;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.IdentityBean;
import org.apache.commons.lang3.StringUtils;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@ViewScoped
public class CompanyEditController extends BaseEditBean<Company> {

    private static final Logger LOG = Logger.getLogger(CompanyEditController.class.getName());

    String roleCode;
    String confirmPassword;

    public UserType[] getTypes() {
        return UserType.values();
    }

    @Inject
    ParkGarauService<Company> companyParkGarauService;
    @Inject
    ParkGarauService<CompanyAdmin> companyAdminParkGarauService;
    @Inject
    ParkGarauService<Role> roleParkGarauService;
    @Inject
    ParkGarauService<User> userParkGarauService;
    CompanyAdmin companyAdmin;
    User user;
    UserProfile userProfile;
    List<Role> roles;
    Set<String> userNames;
    List<User> newUsers;

    List<User> users = new ArrayList<>();

    @Inject
    transient IdentityBean identityBean;

    @Override
    @PostConstruct
    protected void init() {
        if (!identityBean.hasAccess(roleCode)) {
            identityBean.authorize();
        }
        userNames = new HashSet<>();
        user = new User();
        userProfile = new UserProfile();
        roles = this.roleParkGarauService.getList();
        newUsers = new ArrayList<>();
    }

    @Override
    public String onSave() {
        try {

            LOG.info("---List of user has been added or edited-----");
            companyParkGarauService.edit(this.instance);
            LOG.info("--Company has been added---");
            if (!users.isEmpty()) {
                userParkGarauService.addAll(newUsers);
            }
            if (!userNames.isEmpty()) {
                companyAdmin.setCompanyCode(this.instance.getCode());
                companyAdmin.setUserName(new ArrayList<>(userNames));
                companyAdminParkGarauService.edit(companyAdmin);
            }

            FacesUtils.addMessage("messages", "Company", "Compnay " + this.instance + " has been edit in database");
        } catch (Exception e) {
            FacesUtils.addMessage("errorMessages", "unable to save" + e.getMessage());
            return "pretty:company_edit";
        }
        return "pretty:company_list";
    }

    public void remove(User user) {
        user.setActive(false);
        user.getAuditInfo().setDelete(true);
        userParkGarauService.edit(user);
        users.remove(user);
        userNames.remove(user.getUserProfile().getEmailId());
    }

    public void addList() {
        user.setUserProfile(userProfile);
        user.setRoleId(roleCode);
        user.setAuditInfo(new AuditInfo());
        boolean isEmailExists = userNames.add(user.getUserName());
        boolean isTrue = users.add(user);

        if (!isEmailExists) {
            FacesUtils.addMessage("errorMessages", "email id already exists, so use unique one");
            users.remove(user);
            user = new User();
        }
        if (!isTrue) {
            FacesUtils.addMessage("errorMessages", "User Already exists");
        } else {
            newUsers.add(user);
        }
        user = new User();
        userProfile = new UserProfile();
        confirmPassword = "";
        LOG.log(Level.INFO, "size of users{0}", users.size());
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
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
        if (this.selectedItem != null && StringUtils.isNotBlank(this.selectedItem.toString())) {
            this.instance = companyParkGarauService.finder(this.selectedItem.toString());
            LOG.log(Level.INFO, "company object{0}", this.instance.toString());

            if (companyAdminParkGarauService.finder(this.instance.getCode()) != null) {
                this.companyAdmin = companyAdminParkGarauService.finder(this.instance.getCode());
                companyAdmin.getUserName().stream().forEach((emailString) -> {
                    users.add(userParkGarauService.findWithAnotherObjectCode(emailString));
                    LOG.log(Level.INFO, "user Email address{0}", emailString);
                    LOG.log(Level.INFO, "size of users{0}", users.size());
                });
                userNames = new HashSet<>(this.companyAdmin.getUserName());
            }

            if (instance == null) {
                FacesUtils.addMessage("messages", "Company", "Unable to find the record");
                return;
            }
        }
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(Set<String> userNames) {
        this.userNames = userNames;
    }

    public List<User> getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(List<User> newUsers) {
        this.newUsers = newUsers;
    }

}
