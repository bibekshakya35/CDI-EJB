/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.modules.company;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import np.com.drose.parkgarau.modules.AbstractEntity;
import np.com.drose.parkgarau.modules.user.User;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "company_admins")
@NamedQueries(value = {
    @NamedQuery(name = CompanyAdmin.FIND_BY_COMPANYCODE, query = "SELECT ca from CompanyAdmin ca WHERE ca.companyCode=:code"),
    @NamedQuery(name = CompanyAdmin.FIND_ALL_COMPANYADMIN, query = "SELECT ca from CompanyAdmin ca"),
    @NamedQuery(name = CompanyAdmin.FIND_BY_USER_ID, query = "SELECT ca FROM CompanyAdmin ca WHERE ca.userNames=:id"),
    @NamedQuery(name = CompanyAdmin.DELETEBYID, query = "DELETE FROM CompanyAdmin ca WHERE ca.id=:id")
})
public class CompanyAdmin implements Serializable, AbstractEntity {

    public static final String FIND_BY_COMPANYCODE = "np.com.drose.parkgarau.modules.company.FIND_BY_COMPANYCODE";
    public static final String FIND_ALL_COMPANYADMIN = "np.com.drose.parkgarau.modules.company.FIND_ALL_COMPANYADMIN";
    public static final String FIND_BY_USER_ID = "np.com.drose.parkgarau.modules.company.FIND_BY_USER_ID";
    public static final String DELETEBYID = "np.com.drose.parkgarau.modules.company.DELETEBYID";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userName", joinColumns = @JoinColumn(name = "user_name"))
    @OrderColumn
    private List<String> userNames;

    @Transient
    private User user;
    //company code
    private String companyCode;

    @Transient
    private Company company;

    @Column(name = "is_active")
    private boolean active=true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getUserName() {
        return userNames;
    }

    public void setUserName(List<String> userNames) {
        this.userNames = userNames;
    }

    @Override
    public String toString() {
        return "CompanyAdmin{" + "id=" + id + ", username=" + userNames.size() + ", user=" + user + ", companyCode=" + companyCode + ", company=" + company + ", active=" + active + '}';
    }

}
