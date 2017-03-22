/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.ws.resource.v1.response;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class DeviceRegistrationResponse extends np.com.drose.parkgarau.api.response.DeviceRegistrationResponse{

    public DeviceRegistrationResponse(String companyName,String productName) {
        super();
        setCompanyName(companyName);
        setProductName(productName);
    }
    
}
