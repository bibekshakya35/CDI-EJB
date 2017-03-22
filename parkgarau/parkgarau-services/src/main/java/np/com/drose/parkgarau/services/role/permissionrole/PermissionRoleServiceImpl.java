/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.role.permissionrole;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.PermissionRole;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.updater.UpdaterAll;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class PermissionRoleServiceImpl implements ParkGarauService<PermissionRole> {

    @Inject
    Updater<PermissionRole> updater;

    @Inject
    UpdaterAll<PermissionRole> UpdaterAll;

    @Inject
    Manager<PermissionRole> manager;

    @Override
    public void add(PermissionRole t) {
        this.updater.save(t);
    }

    @Override
    public void edit(PermissionRole t) {
        this.updater.Update(t);
    }

    @Override
    public List<PermissionRole> getList() {
        return this.manager.getList();
    }

    @Override
    public PermissionRole finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public void addAll(List<PermissionRole> t) {
        this.UpdaterAll.addAll(t);
    }

    @Override
    public void deleteAll(List<PermissionRole> t) {
        this.UpdaterAll.deleteAll(t);
    }

    @Override
    public List<PermissionRole> findList(java.lang.Object code) {
        return this.manager.findAll(code);
    }

    @Override
    public void editAll(List<PermissionRole> t) {
        this.UpdaterAll.updateAll(t);
    }

    @Override
    public void delete(Object t) {
        this.updater.remove(t); //To change body of generated methods, choose Tools | Templates.
    }

}
