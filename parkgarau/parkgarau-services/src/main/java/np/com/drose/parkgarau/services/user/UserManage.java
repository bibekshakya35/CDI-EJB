/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.user.User;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class UserManage implements Manager<User> {

    private static final Logger LOG = Logger.getLogger(UserManage.class.getName());
    
    

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public User finder(java.lang.Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", (String)code);
        return this.entityManagerWrapper.getSingleResult(User.FIND_BY_USERNAME, parameters);
    }

    @Override
    public List<User> getList() {
        return this.entityManagerWrapper.findAll(User.FIND_ALL);
    }

    @Override
    public User findWithAnotherObjectCode(String code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("emailid",code);
        return this.entityManagerWrapper.getSingleResult(User.FIND_BY_USERNAME, parameters);
    }

    @Override
    public User findWithActiveOne(Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", (String)code);
        return this.entityManagerWrapper.getSingleResult(User.FIND_BY_USERNAME_ACTIVE, parameters);
    }

    

    @Override
    public List<User> findByCriteria() {
        LOG.log(Level.INFO, "list available for user with sys_admin{0}", this.entityManagerWrapper.executeDynamicNamedQuery(User.LIST_SYSTEM_ADMIN));
        return this.entityManagerWrapper.executeDynamicNamedQuery(User.LIST_SYSTEM_ADMIN);
    }
    
    
    

}
