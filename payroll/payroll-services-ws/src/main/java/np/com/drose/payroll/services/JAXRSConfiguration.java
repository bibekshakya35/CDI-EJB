package np.com.drose.payroll.services;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(np.com.drose.payroll.services.resource.EmployeeAddResources.class);
        resources.add(np.com.drose.payroll.services.resource.EmployeeResources.class);
        resources.add(np.com.drose.payroll.services.resource.EventAddResurces.class);
        resources.add(np.com.drose.payroll.services.resource.EventListResources.class);
        resources.add(np.com.drose.payroll.services.resource.ImageResource.class);
        resources.add(np.com.drose.payroll.services.resource.MessageResource.class);
        resources.add(np.com.drose.payroll.services.resource.RegisterResources.class);
        resources.add(np.com.drose.payroll.services.resource.SalaryListResources.class);
        resources.add(np.com.drose.payroll.services.resource.tryout.RegionResource.class);
        resources.add(np.com.drose.payroll.services.security.LoginFIlter.class);
        resources.add(np.com.drose.payroll.services.security.PowerByResponseFilter.class);
        resources.add(np.com.drose.payroll.services.security.SecurityFilter.class);
        resources.add(resources.ImageService.class);
    }

}
