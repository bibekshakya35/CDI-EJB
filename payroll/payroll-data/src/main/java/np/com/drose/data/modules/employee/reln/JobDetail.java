/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.reln;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import np.com.drose.data.modules.employee.domain.Employee;

/**
 *
 * @author bibekshakya
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "jobdetails")
public class JobDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @OneToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;
    
    @Enumerated(EnumType.STRING)
    public EmployeeClassification employeeClassification;
    
   
    
    public enum EmployeeClassification{
        Administrative,
        Knowledge,
        Data,
        Other;
        public static String getEmployeeClassification(EmployeeClassification employeeClassification){
            switch(employeeClassification){
                case Administrative:
                    return "Administrative";
                case Knowledge:
                    return "Knowledge";
                case Data:
                    return "Data";
                case Other:
                    return "Other";
                default:
                    return null;
            }
        }
    }
    
   
    @Enumerated(EnumType.STRING)
    @Column(name = "role_employee")
    public EmployeeRole employeeRole;

    public enum EmployeeRole {
        Developer,
        QA,
        SystemAnalyst,
        SystemTesting,
        WebDesigner,
        Project_Manager,
        HR_Manager,
    }

    @Column(name = "job_specification")
    private String jobSpecification;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift")
    public Shift shift;

    public enum Shift {
        Morning,
        Day,
        Night
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    public Department department;

    public enum Department {
        Manufacturing,
        Sales_Marketing,
        Human_Resource,
        Finance,
        Research_And_Development
    }
     public EmployeeClassification getEmployeeClassification() {
        return employeeClassification;
    }

    public void setEmployeeClassification(EmployeeClassification employeeClassification) {
        this.employeeClassification = employeeClassification;
    }
    

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.jobId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JobDetail other = (JobDetail) obj;
        if (this.jobId != other.jobId) {
            return false;
        }
        return true;
    }

   

}
