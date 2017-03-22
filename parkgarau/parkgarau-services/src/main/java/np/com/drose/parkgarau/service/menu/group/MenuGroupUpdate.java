/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.menu.group;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.menu.group.MenuGroup;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class MenuGroupUpdate implements Updater<MenuGroup> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Instance<GeneralValidator> validators;
    @Inject
    Manager<MenuGroup> menuGroupManage;

    @Override
    public void Update(MenuGroup t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (menuGroupManage.finder(t.getId()) == null) {
            throw new ParkGarauException("Menu Group doesnot exist");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(MenuGroup t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (menuGroupManage.finder(t.getId()) != null) {
            throw new ParkGarauException("Menu Group doesnot exist");
        }
        this.entityManagerWrapper.persist(t);
    }

}
