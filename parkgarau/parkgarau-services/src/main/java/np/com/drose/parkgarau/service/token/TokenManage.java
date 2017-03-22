/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.token.Token;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class TokenManage implements Manager<Token> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    @Override
    public Token finder(Object code) {
        Map<String, Object> paramaters = new HashMap<>();
        paramaters.put("code", (String) code);
        return this.entityManagerWrapper.getSingleResult(Token.FIND_BY_TOKENCODE, paramaters);
    }

    @Override
    public Token findWithAnotherObjectCode(String code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code",(String) code);
        return this.entityManagerWrapper.getSingleResult(Token.FIND_TOKEN_USERNAME, parameters);
    }
    
    @Override
    public List<Token> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
