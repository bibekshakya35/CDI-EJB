/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company.admin;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.CompanyAdmin;
import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyAdminUpdate implements Updater<CompanyAdmin> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Inject
    Manager<CompanyAdmin> manager;

    @Inject
    Instance<GeneralValidator> validators;

    @Override
    public void Update(CompanyAdmin t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (this.manager.finder(t.getId()) == null) {
            throw new ParkGarauException("Company doesnot Exist");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(CompanyAdmin t) {
        validators.forEach((v) -> {
            v.validate(t.getId());
        });
        if (this.manager.finder(t.getId()) != null) {
            throw new ParkGarauException("Company already exists");
        }
        this.entityManagerWrapper.persist(t);
    }

    @Override
    public void remove(Object t) {
       this.entityManagerWrapper.remove(t);
    }

}
