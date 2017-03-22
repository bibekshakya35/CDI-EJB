/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.reln;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import np.com.drose.data.modules.employee.domain.Employee;

/**
 *
 * @author bibekshakya
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "contacts")
public class Contact implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @OneToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.contactId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (this.contactId != other.contactId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "contactId=" + contactId + ", phoneNumber=" + phoneNumber + ", mobileNumber=" + mobileNumber + ", employee=" + employee + '}';
    }
    
    
    
    
}
