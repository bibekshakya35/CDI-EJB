/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service.event.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.data.EntityManagerWrapper;
import np.com.drose.data.modules.employee.domain.event.Event;
import np.com.drose.data.modules.employee.service.event.EventDTO;

/**
 *
 * @author bibekshakya
 */

@Named
@RequestScoped
public class EventDtoImpl  implements EventDTO{
  @EJB
  EntityManagerWrapper entityManagerWrapper;

    @Override
    public List<Event> getEventList() {
        return entityManagerWrapper.findAll(Event.FIND_ALL);
    }

    @Override
    public void insertEvent(Event event) {
        entityManagerWrapper.persist(event);
    }
  
  
}
