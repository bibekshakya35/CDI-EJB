/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.dto.filter;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class VehicleTypeFilter implements Serializable {

    private Date createdFromDate;
    private Date createdToDate;
    private String vehicleCode;

    public VehicleTypeFilter() {
    }

    public VehicleTypeFilter(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getSubmittableStatus() {
        if (StringUtils.isBlank(this.vehicleCode)) {
            return "Invalid Request";
        }

        return "Required Field Missing";
    }

    private String appendQoute(Object value) {
        return "'" + value + "'";
    }

    public String getDetailQuery() {
        return "Select e from VehicleCategory e " + this.prepareCriteria();
    }

    public String countQuery() {
        return "Select Count(e) from VehicleCategory e " + this.prepareCriteria();
    }

    private String prepareCriteria() {
        StringBuilder criteria = new StringBuilder("WHERE ");
        criteria.append(" e.code = ").append(this.appendQoute(this.vehicleCode));

        if (this.createdFromDate != null) {
            criteria.append(" AND auditInfo.createdOn BETWEEN ").append(this.appendQoute(this.createdFromDate)).append(" AND ").append(this.appendQoute(this.getCreatedToDate()));
        }
        return criteria.toString();
    }

    public Date getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(Date createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public Date getCreatedToDate() {
        if (this.createdToDate == null) {
            return this.createdFromDate;
        }
        return createdToDate;
    }

    public void setCreatedToDate(Date createdToDate) {
        this.createdToDate = createdToDate;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    @Override
    public String toString() {
        return "VehicleTypeFilter{" + "createdFromDate=" + createdFromDate + ", createdToDate=" + createdToDate + ", vehicleCode=" + vehicleCode + '}';
    }

}
