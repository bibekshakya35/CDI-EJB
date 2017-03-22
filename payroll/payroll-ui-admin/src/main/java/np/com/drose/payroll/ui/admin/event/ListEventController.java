/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.event;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.event.Event;
import np.com.drose.payroll.services.event.EventServices;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class ListEventController {

    @Inject
    EventServices eventServices;
    
    List<Event> listEvents;

    public List<Event> getListEvents() {
        return listEvents;
    }

    public void setListEvents(List<Event> listEvents) {
        this.listEvents = listEvents;
    }
    
    @PostConstruct
    public void init(){
        listEvents = eventServices.getAllEvent();
    }
    
}
