/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class LogoutBean {
    public void doLogout(){
        HTTPUtils.invalidateSession();
    }
}
