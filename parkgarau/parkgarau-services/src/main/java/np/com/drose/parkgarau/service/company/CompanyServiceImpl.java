/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.service.company;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.api.manager.Manager;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.api.updater.Updater;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class CompanyServiceImpl implements ParkGarauService<Company> {

    private static final Logger LOG = Logger.getLogger(CompanyServiceImpl.class.getName());
    
    @Inject
    Manager<Company> manager;
    @Inject
    Updater<Company> updater;

    @Override
    public void add(Company t) {
        updater.save(t);
    }

    @Override
    public void addAll(List<Company> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Company> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Company t) {
        updater.Update(t);
    }

    @Override
    public List<Company> getList() {
        return this.manager.getList();
    }

    @Override
    public Company finder(java.lang.Object code) {
        LOG.log(Level.INFO, "inside the finder>>>>>>>>{0}", this.manager.finder(code));
        return this.manager.finder(code);
    }


    @Override
    public List<Company> findList(java.lang.Object code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
