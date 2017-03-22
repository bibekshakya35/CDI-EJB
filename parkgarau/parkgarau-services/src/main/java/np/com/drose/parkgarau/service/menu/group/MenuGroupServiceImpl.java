/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.menu.group;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.menu.group.MenuGroup;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class MenuGroupServiceImpl implements ParkGarauService<MenuGroup> {

    @Inject
    Manager<MenuGroup> menuGroupManager;
    @Inject
    Updater<MenuGroup> menuGroupUpdater;

    @Override
    public void add(MenuGroup t) {
        menuGroupUpdater.save(t);
    }

    @Override
    public void addAll(List<MenuGroup> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<MenuGroup> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(MenuGroup t) {
        menuGroupUpdater.Update(t);
    }

    @Override
    public List<MenuGroup> getList() {
        return this.menuGroupManager.getList();
    }

    @Override
    public MenuGroup finder(Object code) {
        return menuGroupManager.finder(code);
    }

    @Override
    public List<MenuGroup> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
