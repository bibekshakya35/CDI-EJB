package np.com.drose.parkgarau.modules;

/**
 *
 * @author kshitij
 */
public interface Auditable {
    public AuditInfo getAuditInfo();

    public void setAuditInfo(AuditInfo auditInfo);
}
