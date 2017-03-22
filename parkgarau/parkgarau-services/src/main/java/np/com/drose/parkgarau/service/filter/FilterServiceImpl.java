/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.service.filter;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import np.com.drose.parkgarau.EntityManagerWrapper;
import np.com.drose.parkgarau.api.FilterService;


/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @param <T>
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Stateless
public class FilterServiceImpl<T> implements FilterService<T>{

    @EJB
    EntityManagerWrapper entityManagerWrapper;
    
    @Override
    public List<T> getResult(String namedQuery) {
        return this.entityManagerWrapper.executeDynamicQuery(namedQuery);
    }
    
}
