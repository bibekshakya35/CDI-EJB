/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.service.event;

import java.util.List;
import np.com.drose.data.modules.employee.domain.event.Event;

/**
 *
 * @author bibekshakya
 */
public interface EventDTO {
    public List<Event> getEventList();
    
    public void insertEvent(Event event);
    
    
}
