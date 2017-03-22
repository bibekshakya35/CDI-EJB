/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.role;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.pages.BaseListBean;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@ViewScoped
public class RoleListController extends BaseListBean<Role> {

    @Inject
    ParkGarauService<Role> parkGarauService;
    @Inject
    transient IdentityBean identityBean;

    @Override
    public void fillList() {
        if (!identityBean.hasAccess("ROLE_VIEW")) {
            this.identityBean.authorize();
        }

        this.list = this.parkGarauService.getList();
    }
}
