package np.com.drose.parkgarau.grouppermission;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.pages.BaseListBean;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@RequestScoped
public class GroupPermissionListController extends BaseListBean<GroupPermission> {

    @Inject
    ParkGarauService<GroupPermission> groupPermissionService;

    @Inject transient IdentityBean identityBean;
    
    /*@PostConstruct
    public void init() {
        super.init();
    }*/

    @Override
    public void fillList() {
        if(!identityBean.hasAccess("ROLE_VIEW")){
            this.identityBean.authorize();
        }
        this.list = this.groupPermissionService.getList();
        
    }
    
}
