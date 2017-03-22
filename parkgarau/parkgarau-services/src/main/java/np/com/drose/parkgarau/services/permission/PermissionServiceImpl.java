/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.services.permission;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class PermissionServiceImpl implements ParkGarauService<Permission>{
    @Inject
    Updater<Permission> updater;
    
    @Inject
    Manager<Permission> manager;
    
    @Override
    public void add(Permission t) {
        this.updater.save(t);
    }

    @Override
    public void addAll(List<Permission> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Permission> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Permission t) {
        this.updater.Update(t);
    }

    @Override
    public List<Permission> getList() {
        return this.manager.getList();
    }

    @Override
    public Permission finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public List<Permission> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
