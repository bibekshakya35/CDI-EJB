/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.payroll.ui.admin.login.utility.HTTPUtils;

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
