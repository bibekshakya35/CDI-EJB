/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.menu.group;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Entity
@Table(name = "menu_group")
@NamedQueries(value = {
    @NamedQuery(name = MenuGroup.FIND_BY_ID, query = "SELECT m FROM MenuGroup m where m.id=:code"),
    @NamedQuery(name = MenuGroup.FIND_ALL, query = "SELECT m FROM MenuGroup m")
})
public class MenuGroup implements Serializable {

    public static final String FIND_BY_ID = "np.com.drose.parkgarau.menu.group.FIND_BY_ID";
    public static final String FIND_ALL = "np.com.drose.parkgarau.menu.group.FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int menuId;
    private String roleCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public MenuGroup() {
    }

    public MenuGroup(int menuId, String roleCode) {
        this.menuId = menuId;
        this.roleCode = roleCode;
    }
    

}
