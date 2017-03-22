package np.com.drose.parkgarau.modules.parkingmanagement;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * The {@code ParkingLog} class represents business logic attributes for
 * ParkingGarau System. This class provide the require attributes that helps us
 * to perform real time operation on parking system.
 *
 * @since Build {ParkGarau 1.0} (19 October 2016)
 * @author Bibek Shakya
 */
public class ParkingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Vehicle plate number is mandatory")
    @Column(name = "vehicleplatenumber", unique = true)
    private String vehiclePlateNumber;

    @NotNull(message = "vehicle name is mandatory")
    @Column(name = "vehicle_name")
    private String vehicleName;

    @NotNull
    private String vehicleType;

    @NotNull
    private String vehicleSubType;

    @NotNull
    private String vehicleColor;

    @NotNull
    private Date inTime;

    private Date outTime;

    private double totalCharge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleSubType() {
        return vehicleSubType;
    }

    public void setVehicleSubType(String vehicleSubType) {
        this.vehicleSubType = vehicleSubType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

}
