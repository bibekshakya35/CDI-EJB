package np.com.drose.parkgarau.api.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Manager<T>} interface take a class as generic for T instance. As
 * per the name, All selection operation perform in the service layer need to
 * implement this contract.
 *
 * @version $Revision: 1.1.1.1 $
 * @param <T>
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public interface Manager<T> {

    T finder(java.lang.Object code);


    /**
     *
     * @param code
     * @return t as an object
     */
    default T findWithAnotherObjectCode(String code) {
        T t = null;
        return t;
    }

    List<T> getList();

    /**
     * this subroutine is for listing the List of row which have Associative
     * relation between two object
     *
     * @param code attribute of specified column or unique represention of
     * object
     * @return {@code List<T>}
     */
    default List<T> findAll(java.lang.Object code) {
        List<T> list = new ArrayList<>();
        return list;
    }
    
    /**
     * get the Object who has been active
     * @param code which can be String, Integer
     * @return T which can be object    
     */
    default T findWithActiveOne(Object code){
        T t=null;
        return t;
    }
    
    /**
     * Dynamic query 
     * @return 
     */
    default List<T> findByCriteria() {
        List<T> list = new ArrayList<>();
        return list;
    }
    

}
