/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.users.preference;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.manager.UserUpdater;
import np.com.drose.parkgarau.security.FacesUtils;
import np.com.drose.parkgarau.security.HTTPUtils;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class PreferenceBean implements Serializable {

    private String oldPassword;
    private String newPassword;
    private String rePassword;

    @Inject
    UserUpdater userUpdater;
    
    private static final Logger LOG = Logger.getLogger(PreferenceBean.class.getName());

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String onChangePassword() {
        LOG.info(">>>>>>>>>>>>>>>>>INSIDE PASSWORD CHANGED BUTTON");
        if (!this.newPassword.equals(rePassword)) {
            FacesUtils.addMessage("messages", "CHANGING PASSWORD", "SORRY, password you have enter did not match");
            return null;
        }
        try {
            boolean isChanged = this.userUpdater.changedPassword(HTTPUtils.getSessionIdentity().getUserName(), newPassword, oldPassword);
            if (isChanged) {
                FacesUtils.addMessage("messages", "Success!!!", "Password changed sucessfully");
                LOG.info("SUCCESS");
                return "pretty:home";
            }
            

        } catch (Exception e) {
            LOG.log(Level.INFO, "ERROR 0{0}", e);
            FacesUtils.addMessage("messages", "Failure", "Password doesnot changed");
            return null;
        }
        return null;
    }

}
