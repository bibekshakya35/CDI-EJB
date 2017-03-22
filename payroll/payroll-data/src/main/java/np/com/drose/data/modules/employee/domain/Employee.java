/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import np.com.drose.data.modules.employee.reln.Attendance;
import np.com.drose.data.modules.employee.reln.Contact;
import np.com.drose.data.modules.employee.reln.JobDetail;
import np.com.drose.data.modules.employee.reln.Salary;
import np.com.drose.data.modules.employee.reln.transientdomain.SalaryTransaction;

/**
 * @author bibekshakya
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e"),
    @NamedQuery(name = Employee.FIND_BY_EMPLOYEECODE, query = "SELECT e FROM Employee e WHERE e.employeeCode =:employeeCode"),
    @NamedQuery(name = Employee.FIND_TODAY_ABSENT, query = "Select count(e.employeeName) FROM Employee e where e.attendance.appearance=:appearance"),
    @NamedQuery(name = Employee.FIND_NO, query = "Select e FROM Employee e where e.employeeStatus=:employeeStatus"),
    
    
})

@XmlRootElement
public class Employee implements Serializable {

    public static final String PREFIX = "np.com.drose.data.modules.employee.domain.";

    public static final String FIND_ALL = PREFIX + "FIND_ALL";

    public static final String FIND_BY_EMPLOYEECODE = PREFIX + "FIND_BY_EMPLOYEECODE";

    public static final String FIND_TODAY_ABSENT = PREFIX + "FIND_TODAY_ABSENT";

    public static final String FIND_NO=PREFIX+"FIND_NO";
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_code")
    private int employeeCode;
    @Size(max = 100)
    @Column(name = "employee_name")
    private String employeeName;
    @Size(max = 100)
    @Column(name = "father_name")
    private String fatherName;
    @Size(max = 100)
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "employee_dob")
    @Past
    @Temporal(TemporalType.DATE)
    private Date employeeDob;
    @Size(max = 50)
    @Column(name = "employee_citizenship_number")
    private String employeeCitizenshipNumber;
    @Size(max = 50)
    @Column(name = "employee_nationality")
    private String employeeNationality;
    @Past
    @Column(name = "employee_date_of_commencement")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date employeeDateOfCommencement;
    @Column(name = "employee_picture")
    private String employeePicture;

    @Enumerated(EnumType.STRING)
    @Column(name = "maritial_status")
    public MaritialStatus maritialStatus;

    public enum MaritialStatus {
        Married,
        Unmarried;

        public static String getMaritialStatus(MaritialStatus maritialStatus) {
            switch (maritialStatus) {
                case Married:
                    return "Married";
                case Unmarried:
                    return "Unmarried";
                default:
                    return null;
            }
        }
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    public Gender sex;

    public enum Gender {
        Male,
        Female;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "job_position")
    public Position jobPosition;

    public enum Position {
        Intern,
        Trainee,
        Entry,
        Junior,
        Mid,
        Senior;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Salary salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private JobDetail detail;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Attendance attendance;
   
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus=EmployeeStatus.Active;
    
    public enum EmployeeStatus{
        Active,Unactive;
        public static String getEmploeStatus(EmployeeStatus employeeStatus){
            switch(employeeStatus){
                case Active:
                    return "Active";
               case Unactive:
                   return "Unactive";
              default:
                  return null;
            }
        }
    }
    
    @Transient
    private SalaryTransaction salaryTransaction;

    public SalaryTransaction getSalaryTransaction() {
        return salaryTransaction;
    }

    public void setSalaryTransaction(SalaryTransaction salaryTransaction) {
        this.salaryTransaction = salaryTransaction;
    }

    
    
    

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Employee() {
    }

    public Employee(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public int getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Date getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(Date employeeDob) {
        this.employeeDob = employeeDob;
    }

    public String getEmployeeCitizenshipNumber() {
        return employeeCitizenshipNumber;
    }

    public void setEmployeeCitizenshipNumber(String employeeCitizenshipNumber) {
        this.employeeCitizenshipNumber = employeeCitizenshipNumber;
    }

    public String getEmployeeNationality() {
        return employeeNationality;
    }

    public void setEmployeeNationality(String employeeNationality) {
        this.employeeNationality = employeeNationality;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Date getEmployeeDateOfCommencement() {
        return employeeDateOfCommencement;
    }

    public void setEmployeeDateOfCommencement(Date employeeDateOfCommencement) {
        this.employeeDateOfCommencement = employeeDateOfCommencement;
    }

    public String getEmployeePicture() {
        return employeePicture;
    }

    public void setEmployeePicture(String employeePicture) {
        this.employeePicture = employeePicture;
    }

    public JobDetail getDetail() {
        return detail;
    }

    public void setDetail(JobDetail detail) {
        this.detail = detail;
    }

    public MaritialStatus getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(MaritialStatus maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Position getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(Position jobPosition) {
        this.jobPosition = jobPosition;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.employeeCode;
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
        final Employee other = (Employee) obj;
        return this.employeeCode == other.employeeCode;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

}
