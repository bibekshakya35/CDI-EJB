/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.menu.Menu;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class MenuManage implements Manager<Menu> {

    @EJB
    EntityManagerWrapper wrapper;

    @Override
    public Menu finder(Object code) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("code", (Integer) code);
        return this.wrapper.getSingleResult(Menu.FIND_BY_ID, parameters);
    }

    @Override
    public List<Menu> getList() {
        return this.wrapper.findAll(Menu.FIND_ALL);
    }

    @Override
    public List<Menu> findAll(java.lang.Object code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code", (Integer)code);
        return this.wrapper.findAll(Menu.FIND_BY_PARENT, parameters);
    }
    
    
}
