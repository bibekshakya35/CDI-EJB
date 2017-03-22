package np.com.drose.parkgarau.modules;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The class {@code AuditLog} is the representation of an object which
 * transaction are recorded in this core Object. {@code AuditLog} helps to
 * provide information about the change in the different object and manage the
 * record as per the request of cross cutting function.
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Embeddable
public class AuditInfo implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "audit_createdOn")
    private Date createdOn;

    @Column(name = "audit_createdBy")
    private String createdBy;

    @Column(name = "audit_modifiedBy")
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "audit_modifiedOn")
    private Date modifiedOn;

    @Column(name = "audit_verifiedBy")
    private String verifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "audit_verifiedOn")
    private Date verifiedOn;

    @Column(name = "is_delete")
    private boolean delete = true;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Date getVerifiedOn() {
        return verifiedOn;
    }

    public void setVerifiedOn(Date verifiedOn) {
        this.verifiedOn = verifiedOn;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public AuditInfo() {
    }

    public AuditInfo(Date createdOn, String createdBy, String modifiedBy, Date modifiedOn, String verifiedBy, Date verifiedOn) {
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.verifiedBy = verifiedBy;
        this.verifiedOn = verifiedOn;
    }
    

    

    /**
     * which table are auditing
     */
}
