/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.token;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.AuditInfo;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "tbl_tokens")
@NamedQueries(value = {
    @NamedQuery(name = Token.FIND_BY_TOKENCODE, query = "SELECT t FROM Token t WHERE t.authToken=:code AND t.active=TRUE"),
    @NamedQuery(name = Token.FIND_TOKEN_USERNAME, query = "SELECT t FROM Token t WHERE t.username=:code AND t.active=TRUE")
})
public class Token implements Serializable, AbstractEntity {

    public static final String FIND_BY_TOKENCODE = "np.com.drose.parkgarau.modules.token.FIND_BY_TOKENCODE";
    public static final String FIND_TOKEN_USERNAME = "np.com.drose.parkgarau.modules.token.FIND_TOKEN_USERNAME";

    @Id
    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "username")
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date")
    private Date expiredOn;

    @Column(name = "is_active")
    private boolean active = true;

    @Embedded
    private AuditInfo auditInfo;

    public Token() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Date getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Date expiredOn) {
        this.expiredOn = expiredOn;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
    
    @PrePersist
    public void init() {
        this.auditInfo.setCreatedOn(new Date());
        this.auditInfo.setModifiedOn(new Date());
        this.auditInfo.setVerifiedOn(new Date());
    }

    public Token(String username, String authToken, Date expiredOn, AuditInfo auditInfo) {
        this.username = username;
        this.authToken = authToken;
        this.expiredOn = expiredOn;
        this.auditInfo = auditInfo;
    }

}
