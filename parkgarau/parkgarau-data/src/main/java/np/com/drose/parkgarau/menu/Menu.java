/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.menu;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "menu")
@NamedQueries(value = {
    @NamedQuery(name = Menu.FIND_BY_ID, query = "SELECT m FROM Menu m WHERE m.id=:code"),
    @NamedQuery(name = Menu.FIND_ALL, query = "SELECT m FROM Menu m"),
    @NamedQuery(name = Menu.FIND_BY_PARENT,query = "SELECT m FROM Menu m WHERE m.parentId=:code")
})
public class Menu implements Serializable {

    public static final String FIND_BY_ID = "np.com.drose.parkgarau.menu.FIND_BY_ID";
    public static final String FIND_ALL = "np.com.drose.parkgarau.menu.FIND_All";
    public static final String FIND_BY_PARENT = "np.com.drose.parkgarau.menu.FIND_BY_PARENT";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", unique = true)
    private String text;

    @Column(name = "iconCls")
    private String iconCls;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "className")
    private String className;
    
    @Column(name = "is_leaf")
    private boolean leaf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Menu() {
    }

    public Menu(String text, String iconCls, int parentId, String className, boolean leaf) {
        this.text = text;
        this.iconCls = iconCls;
        this.parentId = parentId;
        this.className = className;
        this.leaf = leaf;
    }

    
    

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

}
