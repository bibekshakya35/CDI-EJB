/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.security;

import javax.ejb.Stateful;
import javax.inject.Named;
import np.com.drose.payroll.ui.admin.login.utility.HTTPUtils;

/**
 *
 * @author bibekshakya
 */
@Named
@Stateful
public class IdentityBean {
    private Identity identity;
    
    public Identity getIdentity(){
       return HTTPUtils.getSessionIdentity();
    }
}
