/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.menu;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.menu.Menu;
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
public class MenuUpdate implements Updater<Menu> {

    @Inject
    Instance<GeneralValidator> validators;

    @Inject
    Manager<Menu> menuManager;

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public void Update(Menu t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (menuManager.finder(t.getId()) == null) {
            throw new ParkGarauException("Menu doesnot exits");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(Menu t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (menuManager.finder(t.getId()) != null) {
            throw new ParkGarauException("Menu already exits");
        }
        this.entityManagerWrapper.persist(t);
    }

}
