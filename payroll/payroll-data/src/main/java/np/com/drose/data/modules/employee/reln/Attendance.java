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
@Entity
@Table(name = "attendances")
public class Attendance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private int attendanceId;

    @Column(name = "present_day")
    private int presentDay = 0;

    @Column(name = "absent_day")
    private int absentDay = 0;

    @Enumerated(EnumType.STRING)
    private AnnualLeave annualLeave = AnnualLeave.Other;

    @OneToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;
    
    public static final String FIND_ABSENT_TODAY = "np.com.drose.data.modules.employee.reln.FIND_ABSENT_TODAY";

    @Enumerated(EnumType.STRING)
    private Appearance appearance=Appearance.Absent;
     
    public enum Appearance{
        Present,
        Absent;
        
        public static String getAppearance(Appearance appearance){
            switch(appearance){
                case Present:
                    return "Present";
                case Absent:
                    return "Absent";
                default:
                    return null;
            }
        }
        
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }
   

    public int getPresentDay() {
        return presentDay;
    }

    public void setPresentDay(int presentDay) {
        this.presentDay = presentDay;
    }

    public int getAbsentDay() {
        return absentDay;
    }

    public void setAbsentDay(int absentDay) {
        this.absentDay = absentDay;
    }

    public AnnualLeave getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(AnnualLeave annualLeave) {
        this.annualLeave = annualLeave;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
