/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.employee.employeecontroller.pagination;

import java.util.Comparator;
import np.com.drose.data.modules.employee.domain.Employee;
import org.primefaces.model.SortOrder;

 
public class LazySorter implements Comparator<Employee> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        try {
            Object value1 = Employee.class.getField(this.sortField).get(o1);
            Object value2 =Employee.class.getField(this.sortField).get(o2);
            int value =((Comparable)value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value :-1*value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
 
}