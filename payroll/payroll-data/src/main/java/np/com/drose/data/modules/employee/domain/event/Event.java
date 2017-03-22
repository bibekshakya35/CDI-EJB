/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.domain.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 *
 * @author bibekshakya
 */
                                        @Entity
@Table(name = "events")
@NamedQueries({
    @NamedQuery(name = Event.FIND_ALL,query="Select e from Event e")
})
public class Event implements Serializable{
    
    public static final String PREFIX="np.com.drose.data.modules.employee.domain.event.";
    public static final String FIND_ALL=PREFIX+"FIND_ALL";
    
    @Id
    @GeneratedValue
    private int eventId;
    

    private String dateofEvent;
    
    private String content;
    
    private String header;
    
    private String place;
    
    private String specification;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDateofEvent() {
        return dateofEvent;
    }

    public void setDateofEvent(String dateofEvent) {
        this.dateofEvent = dateofEvent;
    }

    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    
    
    
    
}
