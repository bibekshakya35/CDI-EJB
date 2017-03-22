/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.token;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.modules.token.Token;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class TokenServiceImpl implements ParkGarauService<Token>{
    
    @Inject Manager<Token> tokenManager;
    @Inject Updater<Token> tokenUpdater;

    @Override
    public void add(Token t) {
        this.tokenUpdater.save(t);
    }

    @Override
    public void addAll(List<Token> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Token> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Token t) {
        this.tokenUpdater.Update(t);
    }

    @Override
    public List<Token> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Token finder(Object code) {
        return this.tokenManager.finder(code);
    }

    @Override
    public List<Token> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Token findWithAnotherObjectCode(String code) {
        return this.tokenManager.findWithAnotherObjectCode(code);
    }
    
    

}
