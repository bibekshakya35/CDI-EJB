/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company.branch;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.api.updater.Updater;
import np.com.drose.parkgarau.modules.company.CompanyBranch;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyBranchServiceImpl implements ParkGarauService<CompanyBranch> {

    @Inject
    Manager<CompanyBranch> manager;
    @Inject
    Updater<CompanyBranch> updater;

    @Override
    public void add(CompanyBranch t) {
        updater.save(t);
    }

    @Override
    public void addAll(List<CompanyBranch> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<CompanyBranch> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CompanyBranch t) {
        updater.Update(t);
    }

    @Override
    public List<CompanyBranch> getList() {
        return manager.getList();
    }

    @Override
    public CompanyBranch finder(Object code) {
        return this.manager.finder(code);
    }

    @Override
    public List<CompanyBranch> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyBranch findWithAnotherObjectCode(String code) {
        return this.manager.findWithAnotherObjectCode(code);
    }
    

}
