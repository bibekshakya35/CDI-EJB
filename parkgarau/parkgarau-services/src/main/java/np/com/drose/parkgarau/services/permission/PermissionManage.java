/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.permission.Permission;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class PermissionManage implements Manager<Permission> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    Map<String, Object> parameters;

    @Override
    public Permission finder(java.lang.Object code) {
        parameters = new HashMap<>();
        parameters.put("code",(String)code);
        return this.entityManagerWrapper.getSingleResult(Permission.FIND_BY_CODE, parameters);
    }

    @Override
    public List<Permission> getList() {
        return this.entityManagerWrapper.findAll(Permission.FIND_ALL);
    }

}
