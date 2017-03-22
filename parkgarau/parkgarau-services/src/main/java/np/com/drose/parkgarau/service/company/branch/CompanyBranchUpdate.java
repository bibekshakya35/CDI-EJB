/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company.branch;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.modules.company.CompanyBranch;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyBranchUpdate implements Updater<CompanyBranch> {

    @Inject
    Manager<CompanyBranch> manager;
    @Inject
    Instance<GeneralValidator> generalValidators;
    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public void Update(CompanyBranch t) {
        generalValidators.forEach((v) -> {
            v.validate(t.getBranchCode());
        });
        if (this.manager.finder(t.getBranchCode()) == null) {
            throw new ParkGarauException("Doesnot have record in database");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(CompanyBranch t) {
        generalValidators.forEach((v) -> {
            v.validate(t.getBranchCode());
        });
        if (this.manager.finder(t.getBranchCode()) != null) {
            throw new ParkGarauException("already exist have record in database");
        }
        this.entityManagerWrapper.persist(t);
    }

}
