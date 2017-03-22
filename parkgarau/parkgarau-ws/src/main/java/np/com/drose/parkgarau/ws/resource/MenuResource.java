/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.menu.Menu;
import np.com.drose.parkgarau.utils.JSONUtils;
import np.com.drose.parkgarau.ws.dto.Items;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Named
@RequestScoped
@Path("/park")
public class MenuResource {
    
    @Inject
    ParkGarauService<Menu> menuGarauService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/menu")
    public Response menuItem() {
        Map<String, List<Items>> map = new HashMap<>();
        Menu menu = menuGarauService.finder(1);
        List<Menu> menuList = menuGarauService.findList(1);
        Items items = new Items.Builder(menu.getId())
                .nameText(menu.getText())
                .withIconCls(menu.getIconCls())
                .withLeaf(menu.isLeaf())
                .withClassName(null)
                .withParentId(null)
                .withChildItem(menuList).buildItems();
        List<Items> itemses = new ArrayList<>();
        itemses.add(items);
        map.put("items", itemses);
        
        return Response.ok().entity(JSONUtils.objectToJson(itemses)).build();
    }
}
