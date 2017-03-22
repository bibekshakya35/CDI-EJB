/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class FacesUtils {

    public static void addMessage(String id, String title, String message) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(title, message));
        externalContext.getFlash().setKeepMessages(true);
    }
    public static void addMessage(String id, String title) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO : ",title));
        externalContext.getFlash().setKeepMessages(true);
    }
    
}
