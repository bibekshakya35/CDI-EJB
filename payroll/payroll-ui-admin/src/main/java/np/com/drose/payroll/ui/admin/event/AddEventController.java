/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.event;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.event.Event;
import np.com.drose.payroll.services.event.EventServices;
import np.com.drose.payroll.ui.admin.login.utility.FacesUtils;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class AddEventController {
    
    @Inject
    EventServices eventServices;
    
    Event event;
    
    @PostConstruct
    public void init(){
        event = new Event();
    }
    
    public String addEvent(){
        this.eventServices.addEvent(event);
        ExternalContext externalContext =FacesContext.getCurrentInstance().getExternalContext();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messages", new FacesMessage("Record!!!", event.getHeader() +" Has been Added"));
        externalContext.getFlash().setKeepMessages(true);
            
        return "pretty:list_event";
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
 
}
