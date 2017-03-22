/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.services.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.Role;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */

@Stateless
public class RoleManage implements Manager<Role>{
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    

    @Override
    public Role finder(java.lang.Object code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code",(String)code);
        return this.entityManagerWrapper.getSingleResult(Role.FIND_BY_ROLE, parameters);
    }


    @Override
    public List<Role> getList() {
        return this.entityManagerWrapper.findAll(Role.FIND_ALL);
    }

}
