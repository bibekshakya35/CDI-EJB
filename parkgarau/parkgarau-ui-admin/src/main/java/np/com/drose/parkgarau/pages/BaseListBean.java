package np.com.drose.parkgarau.pages;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kshitij
 * @param <T>
 */
public abstract class BaseListBean<T> extends PageBean implements Serializable{
    private static final long serialVersionUID = 1L;
    protected List<T> list;
    protected LazyDataModel<T> lazyList;
    
    @PostConstruct
    protected void init(){
        this.fillList();
    }
    
    public abstract void fillList();

    public BaseListBean() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public LazyDataModel<T> getLazyList() {
        return lazyList;
    }

    public void setLazyList(LazyDataModel<T> lazyList) {
        this.lazyList = lazyList;
    }
        
}
