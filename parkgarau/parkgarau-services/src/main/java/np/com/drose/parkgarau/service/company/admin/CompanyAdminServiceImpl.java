/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company.admin;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.CompanyAdmin;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyAdminServiceImpl implements ParkGarauService<CompanyAdmin> {

    @Inject
    Updater<CompanyAdmin> updater;

    @Inject
    Manager<CompanyAdmin> manager;

    @Override
    public void add(CompanyAdmin t) {
        this.updater.save(t);
    }

    @Override
    public void addAll(List<CompanyAdmin> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<CompanyAdmin> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(CompanyAdmin t) {
        this.updater.Update(t);
    }

    @Override
    public List<CompanyAdmin> getList() {
        return this.manager.getList();
    }

    @Override
    public CompanyAdmin finder(java.lang.Object code) {
        return this.manager.finder(code);
    }


    @Override
    public List<CompanyAdmin> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
