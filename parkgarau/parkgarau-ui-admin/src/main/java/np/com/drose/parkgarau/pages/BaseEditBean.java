package np.com.drose.parkgarau.pages;

import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author kshitij
 * @param <T>
 */
public abstract class BaseEditBean<T> extends PageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    protected T instance;
    protected Object selectedItem;

    public abstract String onSave();

    public abstract String onDelete();

    public abstract String onChangeActiveStatus();

    public abstract Object getSelectedItem();

    public abstract void setSelectedItem(Object item);

    public abstract void loadSelectedItem();

    public BaseEditBean() {
    }

    @PostConstruct
    protected void init() {
        //this.instance = new Class<T>;

        this.loadSelectedItem();
    }

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

}
