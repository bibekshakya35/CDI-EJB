/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Named;
import np.com.drose.parkgarau.modules.user.UserType;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@Stateful
public class IdentityBean {

    private Identity identity;
    private static final Logger LOG = Logger.getLogger(IdentityBean.class.getName());

    public Identity getIdentity() {
        return identity = HTTPUtils.getSessionIdentity();
    }

    public boolean hasAccess(String code) {
        LOG.info("i>>>>>>>>>>>>>>>>>>>>>>>>>>>>>nside hasAccess");
        if (code != null) {
            if (getIdentity().getPermissions().stream().anyMatch((permission) -> (permission.getCode().equals(code)))) {
                return true;
            }
        }
        return false;
    }
    
    

    public void authorize() {
        try {
            FacesUtils.addMessage("messages","Not Authorized");
            HTTPUtils.redirect("/park/home");
        } catch (IOException ex) {
            LOG.log(Level.INFO, "error {0}", ex);
        }
    }
}
