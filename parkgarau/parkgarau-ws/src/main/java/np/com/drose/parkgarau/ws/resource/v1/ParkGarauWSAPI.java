/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.ws.resource.v1;

import np.com.drose.parkgarau.api.request.DeviceRegistrationRequest;
import np.com.drose.parkgarau.api.request.SyncCategoryRequest;
import np.com.drose.parkgarau.api.request.UserLoginRequest;
import np.com.drose.parkgarau.api.request.VehicleInRequest;
import np.com.drose.parkgarau.ws.resource.v1.response.Response;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public interface ParkGarauWSAPI {
    int getVersion();
    Response registerDevice(DeviceRegistrationRequest deviceRegistrationRequest);
    Response authenticateUser(UserLoginRequest userLoginRequest);
    Response syncCategory(SyncCategoryRequest syncCategoryRequest);
    Response vehicleIn(VehicleInRequest vehicleInRequest);
    
}
