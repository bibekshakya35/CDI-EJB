/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.utility;

import java.util.Optional;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import np.com.drose.payroll.ui.admin.login.security.Identity;

/**
 *
 * @author bibekshakya
 */
public class HTTPUtils {
    public static FacesContext getFacesContext(){
           return FacesContext.getCurrentInstance();
    }
    public static HttpServletRequest getServletRequest(){
        return (HttpServletRequest)HTTPUtils.getFacesContext().getExternalContext().getRequest();
    }
    
    //set the session with object identity key will be login.security
    public static void createHttpSession(Identity identity){
        HTTPUtils.getServletRequest().getSession(true).setAttribute(Identity.SESSION_KEY, identity);
    }
    
    public static Identity getSessionIdentity(){
        Optional<Object> sessionData =Optional.ofNullable(HTTPUtils.getServletRequest().getSession(false).getAttribute(Identity.SESSION_KEY));
        //data is there and objec is not null
        if (sessionData.isPresent()) {
            return (Identity)sessionData.get();
        }
        //if null then will return default value
        return Identity.createDefaultInstance();
    }
    public static void invalidateSession(){
        HTTPUtils.getServletRequest().getSession().setAttribute(Identity.SESSION_KEY, null);
        HTTPUtils.getServletRequest().getSession().invalidate();
    }
}
