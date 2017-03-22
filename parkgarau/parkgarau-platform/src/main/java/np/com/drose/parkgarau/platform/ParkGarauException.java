/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.platform;

import javax.ejb.ApplicationException;

/**
 *
 * @author bibek
 */
@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class ParkGarauException extends RuntimeException{
    private int code;
    public static int VALIDATION_FAILED_CODE=400;

    public ParkGarauException(int code, String message) {
        super(message);
        this.code=code;
    }

    public ParkGarauException(String message) {
        this(VALIDATION_FAILED_CODE,message);
    }

    public int getCode() {
        return code;
    }
    
    
    
}
