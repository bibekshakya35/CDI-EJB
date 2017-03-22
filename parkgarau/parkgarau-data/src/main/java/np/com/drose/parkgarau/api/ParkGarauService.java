/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.api;

import java.util.List;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @param <T>
 * @since Build {insert version here} (MM YYYY)
 * @author bibek shakya
 */
public interface ParkGarauService<T> {
    
    void add(T t);
    
    void addAll(List<T> t);
    
    void deleteAll(List<T> t);
    
    void edit(T t);
    
    List<T> getList();
    
    T finder(java.lang.Object code);
    
    List<T> findList(java.lang.Object code);
    
    default void editAll(List<T> t) {
        System.out.println("Edit All");
    }
    
    default void delete(Object code) {
        System.out.println("delete All");
    }

    /**
     * Object which has a relation with other object
     *
     * @param code
     * @return
     */
    default T findWithAnotherObjectCode(String code) {
        try {
            T t = null;
            return t;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * active one will be choosen
     *
     * @param code unique attribute
     * @return Object
     */
    default T findWithActiveOne(Object code) {
        T t = null;
        System.out.print("code >>>>>>>>>>>>>>>>>>>>>>> " + code);
        return t;
    }
    default int count(){
        return 0;
    }
    
    
}
