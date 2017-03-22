/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.device.Device;

import np.com.drose.parkgarau.modules.user.User;
import np.com.drose.parkgarau.pages.BaseListBean;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 *
 * @author bibek
 */
@Named
@RequestScoped
public class UserListController extends BaseListBean<User> {

    private int systemAdmin;

    @Inject
    ParkGarauService<User> userServices;

    @Inject
    ParkGarauService<Device> deviceParkGarauService;

    private int countDevice = 0;

    @Inject
    private transient IdentityBean identityBean;

    @Override
    public void fillList() {
        if (!identityBean.hasAccess("USER_VIEW")) {
            this.identityBean.authorize();
        }
        this.list = userServices.getList();
        systemAdmin = this.userServices.count();
        if (deviceParkGarauService.getList().isEmpty()) {
            this.countDevice = 0;
        }
        else{
            this.countDevice = deviceParkGarauService.getList().size();
        }
    }

    public int getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(int systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    public int getCountDevice() {
        return countDevice;
    }


}
