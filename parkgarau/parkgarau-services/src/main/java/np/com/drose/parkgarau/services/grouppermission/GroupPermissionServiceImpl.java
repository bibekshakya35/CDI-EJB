/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.grouppermission;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 *
 * @author bibek
 */
@Stateless
public class GroupPermissionServiceImpl implements ParkGarauService<GroupPermission> {

    @Inject
    Manager<GroupPermission> manager;

    @Inject
    Updater<GroupPermission> updater;

    @Override
    public void add(GroupPermission t) {
        this.updater.save(t);
    }

    @Override
    public void addAll(List<GroupPermission> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<GroupPermission> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(GroupPermission t) {
        this.updater.Update(t);
    }

    @Override
    public List<GroupPermission> getList() {
        return this.manager.getList();
    }

    @Override
    public GroupPermission finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public List<GroupPermission> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupPermission findWithAnotherObjectCode(String code) {
        return this.manager.findWithAnotherObjectCode(code);
    }

}
