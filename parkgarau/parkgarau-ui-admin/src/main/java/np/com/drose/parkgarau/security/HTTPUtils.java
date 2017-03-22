/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class HTTPUtils {

    private static final Logger log = Logger.getLogger(HTTPUtils.class.getName());

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) HTTPUtils.getFacesContext().getExternalContext().getRequest();
    }

    //set the session with object identity key will be login.security
    //create a session user for the transaction
    public static void createHttpSession(Identity identity) {
        HTTPUtils.getServletRequest().getSession(true).setAttribute(Identity.SESSION_KEY, identity);
    }

    public static Identity getSessionIdentity() {
        Optional<Object> sessionData = Optional.ofNullable(HTTPUtils.getServletRequest().getSession(false).getAttribute(Identity.SESSION_KEY));
        //Object is present 
        if (sessionData.isPresent()) {
            return (Identity) sessionData.get();
        }
        return null;
//        return Identity.createDefaultInstance();
    }

    public static void invalidateSession() {
        HTTPUtils.getServletRequest().getSession().setAttribute(Identity.SESSION_KEY, null);
        HTTPUtils.getServletRequest().getSession().invalidate();
    }

    public static void redirect(String url) throws IOException {
        String host = HTTPUtils.getFacesContext().getExternalContext().getRequestContextPath();
        HTTPUtils.getFacesContext().getExternalContext().redirect(host + url);
    }

}
