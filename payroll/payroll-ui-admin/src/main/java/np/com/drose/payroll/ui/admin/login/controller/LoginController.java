/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.controller;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.user.domain.User;
import np.com.drose.data.modules.user.services.impl.UserAddServices;
import np.com.drose.data.modules.user.services.impl.UserFinder;
import np.com.drose.data.modules.user.services.impl.UserNameFind;
import np.com.drose.payroll.ui.admin.login.utility.EncrptionUtils;
import np.com.drose.payroll.ui.admin.login.security.Identity;
import np.com.drose.payroll.ui.admin.login.utility.FacesUtils;
import np.com.drose.payroll.ui.admin.login.utility.HTTPUtils;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    private UserNameFind finder;

    @Inject
    private UserFinder userFinder;

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User.UserROLE[] getRoles() {
        return User.UserROLE.values();
    }

    @Inject
    UserAddServices userAddServices;

    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String addUser() {

        try {
            if(!user.getPassword().equals(confirmPassword)){
                return null;
            }
            String encrption =EncrptionUtils.encrypt(user.getPassword());
            user.setPassword(encrption);
            this.userAddServices.insertUser(user);
            return "pretty:login";

        } catch (Exception e) {

            FacesUtils.addErrorMessage("Password did not Match");
            return null;
        }
    }

    public String login() {
        LOG.info("inside Login .... .... ");
        try {
            String passwordToEncrypt = EncrptionUtils.encrypt(user.getPassword());
            User uservalidation = this.userFinder.findUser(user.getUserName(), passwordToEncrypt);
            String auth_key = "";
            HTTPUtils.createHttpSession(new Identity(uservalidation.getUserName(), auth_key, true, uservalidation.getFullname(), uservalidation.getRoles().toString()));
            return "pretty:home";

        } catch (Exception e) {
            FacesUtils.addErrorMessage("Username and password did not match");
            LOG.info("error in login " + e.getMessage());

            return null;
        }
    }

}
