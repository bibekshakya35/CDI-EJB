package np.com.drose.parkgarau.pages;

import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author kshitij
 * @param <T>
 */
public abstract class BaseAddBean<T> extends PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    protected T instance;
   
    public abstract String onSave(); 

    public BaseAddBean() {
    }
    
    @PostConstruct
    protected void init(){
        //this.instance = new Class<T>;
    }   
        
    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }
}
