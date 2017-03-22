/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import np.com.drose.parkgarau.menu.Menu;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
         
public class Items{

    private int id;

    private String text;

    private String iconCls;

    private String parentId;

    private String className;

    private boolean leaf;

    List<Menu> items;

    public static class Builder {

        private int id;

        private String text;

        private String iconCls;

        private String parentId;

        private String className;

        private boolean leaf;

        List<Menu> items;

        public Builder(int id) {
            this.id = id;
        }

        public Builder nameText(String text) {
            this.text = text;
            return this;
        }

        public Builder withIconCls(String iconCls) {
            this.iconCls = iconCls;
            return this;
        }

        public Builder withParentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Builder withClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder withLeaf(boolean leaf) {
            this.leaf = leaf;
            return this;
        }

        public Builder withChildItem( List<Menu> items) {
            this.items=items;
            return this;
        }

        public Items buildItems() {
            Items items = new Items();
            items.id = id;
            items.text = text;
            items.iconCls = iconCls;
            items.parentId = parentId;
            items.className = className;
            items.leaf = leaf;
            items.items = this.items;
            return items;
        }

    }

    private Items() {
    }

}
