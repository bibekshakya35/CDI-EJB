/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.token;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import np.com.drose.parkgarau.modules.token.Token;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class TokenUpdate implements Updater<Token> {

    @Inject
    Instance<GeneralValidator> generalValidators;
    @Inject
    Manager<Token> tokenManager;
    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public void Update(Token t) {
        generalValidators.forEach((v) -> {
            v.validate(t.getAuthToken());
            v.validate(t.getUsername());
        });
        if (tokenManager.finder(t.getAuthToken()) == null) {
            throw new ParkGarauException("record doesnot exists");
        }
        this.entityManagerWrapper.merge(t);
    }

    @Override
    public void save(Token t) {
        generalValidators.forEach((v) -> {
            v.validate(t.getAuthToken());
            v.validate(t.getUsername());
        });
        if (tokenManager.finder(t.getAuthToken()) != null) {
            throw new ParkGarauException("record already exists");
        }
        this.entityManagerWrapper.persist(t);
    }

}
