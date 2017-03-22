/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.company.branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.CompanyBranch;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class CompanyBranchManage implements Manager<CompanyBranch>{
    @EJB EntityManagerWrapper entityManagerWrapper;

    @Override
    public CompanyBranch finder(Object code) {
        return this.entityManagerWrapper.find(CompanyBranch.class, (String)code);
    }

    @Override
    public CompanyBranch findWithAnotherObjectCode(String code) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("code", (String)code);
        return this.entityManagerWrapper.getSingleResult(CompanyBranch.FIND_COMPANYBRANCH_COMPANY_CODE, parameters);
    }
    
    

    @Override
    public List<CompanyBranch> getList() {
        return this.entityManagerWrapper.findAll(CompanyBranch.FIND_ALL_COMPANY_BRANCH);
    }


}
