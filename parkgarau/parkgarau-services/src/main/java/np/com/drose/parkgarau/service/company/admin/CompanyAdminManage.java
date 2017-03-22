/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.CompanyAdmin;
import np.com.drose.parkgarau.platform.ParkGarauException;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyAdminManage implements Manager<CompanyAdmin> {

    @EJB
    EntityManagerWrapper entityManagerWrapper;

    /**
     *
     * @param code is for getting the companyCode of {@code Company} Object
     * @return {@code CompanyAdmin} Object with the one who are specified by
     * specific code
     */
    @Override
    public CompanyAdmin finder(java.lang.Object code) {
        Map<String, Object> parameters = new HashMap<>();
        
        if (code instanceof String) {
            parameters.put("code",(String)code);
            return this.entityManagerWrapper.getSingleResult(CompanyAdmin.FIND_BY_COMPANYCODE, parameters);
        } else if (code instanceof Integer) {
            parameters.put("code",(Integer)code);
            return this.entityManagerWrapper.getSingleResult(CompanyAdmin.FIND_BY_USER_ID,parameters);
        }
        throw new ParkGarauException("CANNOT FOUND COMPANY ADMIN");
    }

    @Override
    public List<CompanyAdmin> getList() {
        return this.entityManagerWrapper.findAll(CompanyAdmin.FIND_ALL_COMPANYADMIN);
    }

    
}
