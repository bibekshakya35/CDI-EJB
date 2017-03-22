/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.Company;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyManage implements Manager<Company>{
    @EJB
    EntityManagerWrapper entityManagerWrapper;
    private static final Logger LOG = Logger.getLogger(CompanyManage.class.getName());
    
    
    
    @Override
    public Company finder(java.lang.Object code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code",(String)code);
        LOG.info("value found or not >>>>>>>");
        return this.entityManagerWrapper.getSingleResult(Company.FIND_BY_CCODE, parameters);
        
    }


    @Override
    public List<Company> getList() {
        return this.entityManagerWrapper.findAll(Company.FIND_BY_ALL);
    }

    
}
