/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.user;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.user.User;
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
public class UserServiceImpl implements ParkGarauService<User> {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());
    
    @Inject
    Updater<User> updater;
    
    @Inject
    UpdaterAll<User> updaterAll;
    
    @Inject
    Manager<User> manager;
    
    @Override
    public void add(User t) {
        this.updater.save(t);        
    }
    
    @Override
    public void addAll(List<User> t) {
        this.updaterAll.addAll(t);
    }
    
    @Override
    public void deleteAll(List<User> t) {
        this.updaterAll.deleteAll(t);
    }
    
    @Override
    public void edit(User t) {
        this.updater.Update(t);
    }
    
    @Override
    public List<User> getList() {
        return this.manager.getList();
    }
    
    @Override
    public User finder(java.lang.Object code) {
        return this.manager.finder(code);
    }
    
    
    @Override
    public List<User> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void editAll(List<User> t) {
        this.updaterAll.updateAll(t);
    }
    
    @Override
    public User findWithAnotherObjectCode(String code) {
        return this.manager.findWithAnotherObjectCode(code);
    }
    
    @Override
    public void delete(Object code) {
        this.updater.remove(code);
    }

    @Override
    public User findWithActiveOne(Object code) {
        return this.manager.findWithActiveOne(code);
    }

    @Override
    public int count() {
        LOG.log(Level.INFO, "size of count{0}", this.manager.findByCriteria().size());
        return this.manager.findByCriteria().size();
    }
    
    
    
}
