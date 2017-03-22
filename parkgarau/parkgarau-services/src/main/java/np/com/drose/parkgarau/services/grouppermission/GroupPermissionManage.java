/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.services.grouppermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.grouppermission.GroupPermission;

/**
 *
 * @author bibek
 */
public class GroupPermissionManage implements Manager<GroupPermission>{
    @EJB
    EntityManagerWrapper entityManagerWrapper;


    @Override
    public List<GroupPermission> getList() {
        return this.entityManagerWrapper.findAll(GroupPermission.FIND_BY_ALL);
    }

    @Override
    public GroupPermission finder(java.lang.Object code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code",(String)code);
        return this.entityManagerWrapper.getSingleResult(GroupPermission.FIND_BY_CODE, parameters);
    }

    @Override
    public GroupPermission findWithAnotherObjectCode(String code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code", code);
        return this.entityManagerWrapper.getSingleResult(GroupPermission.FIND_BY_CODE_WITH_PERMISSIONS, parameters);
    }
    
}
