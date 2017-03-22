/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.platform;

import javax.ejb.ApplicationException;

/**
 *
 * @author bibekshakya
 */
@SuppressWarnings("serials")
@ApplicationException(rollback =true)
public class PayrollException extends RuntimeException{
    private int code;
    public static int VALIDATION_FAILED_CODE=400;
    public PayrollException(int code,String message){
        super(message);//Constructs a new runtime exception with the specified detail message.
        this.code=code;
    }
    public PayrollException(String message){
        this(VALIDATION_FAILED_CODE, message);
    }

    public int getCode() {
        return code;
    }
    
}
