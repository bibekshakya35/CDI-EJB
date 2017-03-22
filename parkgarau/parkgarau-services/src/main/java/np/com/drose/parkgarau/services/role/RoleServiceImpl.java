/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.role;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.Role;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class RoleServiceImpl implements ParkGarauService<Role> {

    @Inject
    Manager<Role> manager;

    @Inject
    Updater<Role> updater;

    @Override
    public void add(Role t) {
        this.updater.save(t);
    }

    @Override
    public void edit(Role t) {
        this.updater.Update(t);
    }

    @Override
    public List<Role> getList() {
        return this.manager.getList();
    }

    @Override
    public Role finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public void addAll(List<Role> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Role> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> findList(java.lang.Object code) {
        return this.manager.findAll(code);
    }
    

}
