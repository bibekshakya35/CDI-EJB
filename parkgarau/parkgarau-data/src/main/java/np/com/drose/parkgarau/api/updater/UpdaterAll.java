/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.api.updater;

import java.util.List;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @param <T>
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public interface UpdaterAll<T> {
        void addAll(List<T> t);
        void deleteAll(List<T> t);
        default void updateAll(List<T> t){
            System.out.println("updateALl");
        }
    
}