package np.com.drose.payroll.ui.admin.employee.employeecontroller.pagination;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.service.EmployeeListServices;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

 
@Named("dtLazyView")
@javax.faces.view.ViewScoped
public class LazyView implements Serializable {
     
    private LazyDataModel<Employee> lazyModel;
     
    private Employee selectedEmployee;
     
    
    @Inject
    EmployeeListServices service;
     
    @PostConstruct
    public void init(){
          lazyModel = new LazyEmployeeDataModel(service.listEmployee());
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Employee Selected", Integer.toString(((Employee) event.getObject()).getEmployeeCode()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LazyDataModel<Employee> getLazyModel() {
        if (lazyModel==null) {
            lazyModel = new LazyEmployeeDataModel(service.listEmployee());
        }
      
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Employee> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public EmployeeListServices getService() {
        return service;
    }

    public void setService(EmployeeListServices service) {
        this.service = service;
    }
}