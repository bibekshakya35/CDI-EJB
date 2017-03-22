package np.com.drose.parkgarau.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import np.com.drose.parkgarau.modules.Auditable;

/**
 *
 * @author kshitij
 */
public class RecordChangeValidateInterceptor {
    
    private static final Logger LOG = Logger.getLogger(RecordChangeValidateInterceptor.class.getName());
    
    @AroundInvoke
    public Object validateAuditInfo(InvocationContext ic) throws Exception {
        LOG.log(Level.INFO, "Interceptor invoked for {0}", ic.getMethod().getName());
        Object[] params = ic.getParameters();
        
        Auditable auditable = (Auditable) params[0];
        LOG.log(Level.INFO, "function code={0}", auditable.getAuditInfo().getCreatedBy());
        return ic.proceed();
    }
}
