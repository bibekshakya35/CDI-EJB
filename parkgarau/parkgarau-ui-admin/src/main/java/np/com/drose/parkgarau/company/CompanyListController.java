/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.company;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.company.Company;
import np.com.drose.parkgarau.modules.company.CompanyAdmin;
import np.com.drose.parkgarau.pages.BaseListBean;
import np.com.drose.parkgarau.security.IdentityBean;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
public class CompanyListController extends BaseListBean<Company> {

    @Inject
    IdentityBean identityBean;
    @Inject
    ParkGarauService<Company> parkGarauService;

    private int numberCompany = 0;

    @Inject
    ParkGarauService<CompanyAdmin> caParkGarauService;

    private int companyAdminUser;

    private static final Logger LOG = Logger.getLogger(CompanyListController.class.getName());

    @Override
    public void fillList() {
        if (!identityBean.hasAccess("COMPANY_VIEW")) {
            this.identityBean.authorize();
        }
        this.list = parkGarauService.getList();
        if (!list.isEmpty()) {
            list.stream().forEach((l) -> {
                if (l.isActive()) {
                    ++this.numberCompany;
                }
            });
        }
        if (!caParkGarauService.getList().isEmpty()) {
            companyAdminUser = caParkGarauService.getList().size();
        }

    }

    public int getCompanyAdminUser() {
        return companyAdminUser;
    }

    public int getNumberCompany() {
        return numberCompany;
    }

}
