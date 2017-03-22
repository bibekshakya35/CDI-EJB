/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.modules;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "audit_trial")
public class AuditTrial implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "audittrial_id")
    private int id;
    
    @Column(name = "domain_name")
    private String domainName;
    
    @Column(name = "key_id")
    private String keyId;
    
    @Column(name = "oldValue")
    private String oldValue;
    
    @Column(name = "newValue")
    private String newValue;
    
    @Column(name = "enteredBy")
    private String enteredBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enteredOn")
    private Date enteredOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedOn;
    
    private String verifiedBy;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "function_code")
    private FunctionCode functionCode;
    
    public enum FunctionCode{
        ADD,
        MODIFY,
        DELETE;
        public static String getFunctionCode(FunctionCode functionCode){
            switch(functionCode){
                case ADD:return "Add";
                case DELETE:return "Delete";
                case MODIFY:return "Modify";
                default:return null;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredOn() {
        return enteredOn;
    }

    public void setEnteredOn(Date enteredOn) {
        this.enteredOn = enteredOn;
    }

    public Date getVerifiedOn() {
        return verifiedOn;
    }

    public void setVerifiedOn(Date verifiedOn) {
        this.verifiedOn = verifiedOn;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public FunctionCode getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(FunctionCode functionCode) {
        this.functionCode = functionCode;
    }
    
    
}
