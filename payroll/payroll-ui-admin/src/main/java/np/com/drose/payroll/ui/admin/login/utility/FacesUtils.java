/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bibekshakya
 */
public class FacesUtils {
    public static ExternalContext getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    public static HttpSession getSession(){
        return (HttpSession)FacesUtils.getExternalContext().getSession(false);
    }
    public static void invalidatesession(){
        getSession().invalidate();
    }
    public static FacesContext getContext(){
        return FacesContext.getCurrentInstance();
    }
    public static void addSuccessMessage(String msg){
        addMessage(FacesMessage.SEVERITY_INFO,msg);
    }
    public static void addSuccessMessage(String id, String msg){
        final FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(id, faceMsg);
    }
    public static void addErrorMessage(String msg){
        addMessage(FacesMessage.SEVERITY_ERROR,msg);
    }
    public static void addMessage(FacesMessage.Severity severity,String msg){
        final FacesMessage facesMsg = new FacesMessage(severity,"",msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    public static String redirectTo(String page){
        return page+"?faces-redirect=true";
    }
        
}
