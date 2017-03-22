/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.api.updater;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @param <T>
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */

public interface Updater<T> {
    void Update(T t);
    void save(T t);
    default void remove(Object t){
        System.out.println("remove");
    }
   
   
}
