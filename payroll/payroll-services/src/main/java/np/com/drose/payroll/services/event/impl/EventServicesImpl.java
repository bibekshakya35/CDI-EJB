/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.event.impl;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.event.Event;
import np.com.drose.data.modules.employee.service.event.EventDTO;
import np.com.drose.payroll.services.event.EventServices;
import np.com.drose.payroll.services.utills.DateFormatter;

/**
 *
 * @author bibekshakya
 */
@Named
@RequestScoped
public class EventServicesImpl implements EventServices {

    @Inject
    EventDTO eventDTO;

    @Override
    public List<Event> getAllEvent() {

        return this.eventDTO.getEventList();
    }

    @Override
    public void addEvent(Event event) {
        event.setDateofEvent(DateFormatter.getdate(event.getDateofEvent()));
        eventDTO.insertEvent(event);
    }

}
