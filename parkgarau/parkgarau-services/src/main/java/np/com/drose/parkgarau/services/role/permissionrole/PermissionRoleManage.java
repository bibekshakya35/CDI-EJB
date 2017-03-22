/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.role.permissionrole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.role.PermissionRole;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class PermissionRoleManage implements Manager<PermissionRole> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public PermissionRole finder(java.lang.Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id",(Integer)code);
        return this.entityManagerWrapper.getSingleResult(PermissionRole.FIND_BY_PRID, parameters);
    }

    @Override
    public List<PermissionRole> getList() {
        return this.entityManagerWrapper.findAll(PermissionRole.FIND_BY_ALL);
    }

    @Override
    public List<PermissionRole> findAll(java.lang.Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", code);
        return this.entityManagerWrapper.findAll(PermissionRole.FIND_BY_ROLECODE, parameters);
    }

}
