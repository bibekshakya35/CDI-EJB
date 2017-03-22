/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.salary.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.reln.transientdomain.SalaryTransaction;
import np.com.drose.data.modules.employee.service.EmployeeFinderService;
import np.com.drose.data.modules.employee.service.EmployeeListServices;
import np.com.drose.payroll.services.domain.employee.administrative.TopLevelAdminstrative;

/**
 *
 * @author bibekshakya
 */
@Stateless
@LocalBean
public class SalaryCalculationServiceImpl {

    @Inject
    EmployeeFinderService employeeFinderService;

    @Inject
    EmployeeListServices employeeListServices;

    private double actualSalary = 0;
    private double miscellaneous_allowance = 0;
    private double transportation_cost = 0;
    private double insurance_life = 0;

    private double provident_fund = 0;

    private double salary = 0;

    public Employee salaryCalculation(int employeeCode) {
        Employee employee = employeeFinderService.getEmployeeByCode(employeeCode);
        SalaryTransaction salaryTransaction = new SalaryTransaction();
        if (employee.getDetail().employeeClassification.toString().equals("Administrative")) {
            if (employee.getJobPosition().Senior.toString().equals("Senior")) {
                salary = employee.getSalary().getBasicSalary();
                miscellaneous_allowance = TopLevelAdminstrative.MISCELLAEOUS_ALLOWANCE * salary;
                transportation_cost = TopLevelAdminstrative.TRANSPORATION_COST * salary;
                insurance_life = TopLevelAdminstrative.INSURANCE_LIFE * salary;
                provident_fund = TopLevelAdminstrative.PROVIDENT_FUND * salary;
                actualSalary = salary + miscellaneous_allowance + transportation_cost - provident_fund;
                salaryTransaction.setActualSalary(actualSalary);
                salaryTransaction.setInsurance_life(insurance_life);
                salaryTransaction.setMiscellaneous_allowance(miscellaneous_allowance);
                salaryTransaction.setProvident_fund(provident_fund);
                salaryTransaction.setTransportation_cost(transportation_cost);
                employee.setSalaryTransaction(salaryTransaction);

            }

        }

        return employee;

    }

    public List<Employee> getSalaryList() {
        List<Employee> employeesList = employeeListServices.listEmployee();
        List<Employee> newList = new ArrayList<>();
        Employee newemployee = new Employee();
        for (Employee employee : employeesList) {
            newemployee = salaryCalculation(employee.getEmployeeCode());
            newList.add(newemployee);
        }
        return newList;
    }

    public Map<String, String> getSalaryListRest() {
        List<Employee> listEmployees = getSalaryList();
        Map<String, String> employeeMap = new HashMap<>();
        for (Employee listEmployee : listEmployees) {
            String employeeRestUrl = "http://192.168.0.101:8080/payroll-services-ws/api/secured/image/" + listEmployee.getEmployeePicture();
            employeeMap.put("pic", employeeRestUrl);
            employeeMap.put("employeeName", listEmployee.getEmployeeName());
            employeeMap.put("attendance", listEmployee.getEmployeeStatus().toString());
            employeeMap.put("position", listEmployee.getJobPosition().toString());
            employeeMap.put("mobile", listEmployee.getContact().getMobileNumber());
            employeeMap.put("role", listEmployee.getDetail().getEmployeeRole().toString());
            employeeMap.put("level", listEmployee.getDetail().getEmployeeClassification().toString());
            employeeMap.put("department", listEmployee.getDetail().getDepartment().toString());
            employeeMap.put("pf", Double.toString(listEmployee.getSalaryTransaction().getProvident_fund()));
            employeeMap.put("tc", Double.toString(listEmployee.getSalaryTransaction().getTransportation_cost()));
            employeeMap.put("li", Double.toString(listEmployee.getSalaryTransaction().getInsurance_life()));
            employeeMap.put("mc", Double.toString(listEmployee.getSalaryTransaction().getMiscellaneous_allowance()));
            employeeMap.put("bs", Double.toString(listEmployee.getSalary().getBasicSalary()));
            employeeMap.put("as", Double.toString(listEmployee.getSalaryTransaction().getActualSalary()));
        }
        return employeeMap;
    }

}
