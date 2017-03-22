/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.ws.resource.v1.httpstatuscode;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public enum HTTPStatusCode {
    
    
    OK(200),
    CREATED(201),
    BADREQUEST(400),
    UNAUTHORIZED(401);
    private final int httpcode;
    private HTTPStatusCode(int httpcode){
        this.httpcode = httpcode;
    }

    public int getHttpcode() {
        return httpcode;
    }
    

   

    
     
    
}
