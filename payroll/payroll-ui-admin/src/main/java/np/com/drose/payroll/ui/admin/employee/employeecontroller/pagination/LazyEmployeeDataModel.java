/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.employee.employeecontroller.pagination;

/**
 *
 * @author bibekshakya
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import np.com.drose.data.modules.employee.domain.Employee;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class LazyEmployeeDataModel extends LazyDataModel<Employee>{

    private List<Employee> datasource;

    public LazyEmployeeDataModel(List<Employee> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Employee getRowData(String rowKey) {
        for (Employee employee : datasource) {
            if (Integer.toString(employee.getEmployeeCode()).equals(rowKey)) {
                return employee;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Employee employee) {
        return employee.getEmployeeCode();
    }

    @Override
    public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Employee> data = new ArrayList<>();

        //filter
        for (Employee employee : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(employee.getClass().getField(filterProperty).get(employee));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(employee);
            }
        }

        //sort
        if (sortField != null) {
             Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
