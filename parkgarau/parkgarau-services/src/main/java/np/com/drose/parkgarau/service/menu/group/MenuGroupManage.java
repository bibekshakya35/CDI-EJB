/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.menu.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.menu.group.MenuGroup;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class MenuGroupManage implements Manager<MenuGroup> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public MenuGroup finder(Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", (Integer) code);
        return this.entityManagerWrapper.getSingleResult(MenuGroup.FIND_BY_ID, parameters);
    }

    @Override
    public List<MenuGroup> getList() {
        return entityManagerWrapper.findAll(MenuGroup.FIND_ALL);
    }

}
