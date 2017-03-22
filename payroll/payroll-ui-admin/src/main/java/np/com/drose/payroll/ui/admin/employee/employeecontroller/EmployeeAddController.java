/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.employee.employeecontroller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import np.com.drose.data.modules.employee.domain.Employee;
import np.com.drose.data.modules.employee.reln.Attendance;
import np.com.drose.data.modules.employee.reln.Contact;
import np.com.drose.data.modules.employee.reln.JobDetail;
import np.com.drose.data.modules.employee.reln.Salary;
import np.com.drose.data.modules.employee.service.EmployeeAddService;
import np.com.drose.payroll.ui.admin.login.utility.FacesUtils;



import org.omnifaces.util.Messages;

/**
 *
 * @author bibekshakya
 */
@Named
@ViewScoped
public class EmployeeAddController implements Serializable{

    private Employee employee;
    private Contact contact;
    private JobDetail detail;
    private Salary salary;
    private Attendance attendance;
    

    private Part filePart;
    
    
    @Inject
    private EmployeeAddService addEmp;

  
    private static final Logger LOG = Logger.getLogger(EmployeeAddController.class.getName());
    
    @PostConstruct
    public void init(){
        employee=new Employee();
        salary=new Salary();
        detail=new JobDetail();
        contact=new Contact();
        employee.setMaritialStatus(Employee.MaritialStatus.Married);
        employee.setSex(Employee.Gender.Male);
        detail.setDepartment(JobDetail.Department.Finance);
        detail.setShift(JobDetail.Shift.Night);
        detail.setEmployeeRole(JobDetail.EmployeeRole.Developer);
        attendance =new Attendance();
    }
    
    public Employee.Gender[] getGenders(){
        return Employee.Gender.values();
    }
    
    public Employee.MaritialStatus[] getMaritialStatuses(){
        return Employee.MaritialStatus.values();
    }
    
    public Employee.Position[] getPositions(){
        return Employee.Position.values();
    }
    public Employee getEmployee() {
        return employee;
    }
     
    public JobDetail.Department[] getDepartments(){
        return JobDetail.Department.values();
    }
    public JobDetail.EmployeeRole[] getEmployeeRoles(){
        return JobDetail.EmployeeRole.values();
    }
    
    public JobDetail.Shift[] getEmployeeShift(){
        return JobDetail.Shift.values();
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Contact getContact() {
        return contact;
    }
    

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public JobDetail getDetail() {
        return detail;
    }

    public void setDetail(JobDetail detail) {
        this.detail = detail;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public EmployeeAddService getAddEmp() {
        return addEmp;
    }

    public void setAddEmp(EmployeeAddService addEmp) {
        this.addEmp = addEmp;
    }
    
    public JobDetail.Shift[] getShifts(){
        return JobDetail.Shift.values();
    }
    public JobDetail.EmployeeClassification[] getEmployeeClassification(){
        return JobDetail.EmployeeClassification.values();
    }
    

  
    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }

    public void upload() throws IOException{
        File fileUpload = new File("C:/payroll/image");
        String filenameString ="employee";
        File filePath = File.createTempFile(filenameString,".jpg",fileUpload);
        filenameString+=".jpg";
        for(String filename : filePath.toString().split("/",4)){
            employee.setEmployeePicture(filename);
        }
        try(InputStream input = filePart.getInputStream()){
            Files.copy(input, filePath.toPath(),StandardCopyOption.REPLACE_EXISTING);
        }
        
    }
    public String addEmployee(){
        try {
            
            contact.setEmployee(employee);
            salary.setEmployee(employee);
            detail.setEmployee(employee);
            attendance.setEmployee(employee);
            
            employee.setContact(contact);
            employee.setDetail(detail);
            employee.setSalary(salary);
            employee.setAttendance(attendance);
            
            this.addEmp.addEmployee(employee);
            
           
            //this.updateService.
            
            return "pretty:list_employee";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("Employee Record is not add in database, Please check the field correctly");
            return null;
        }
    }
    
}
