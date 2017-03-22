/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.validation.impl;

import np.com.drose.parkgarau.platform.ParkGarauException;
import np.com.drose.parkgarau.api.validator.GeneralValidator;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author bibek
 */
public class GeneralValidation implements GeneralValidator{

    @Override
    public void validate(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ParkGarauException("Code cannot be null");
        }
    }

    @Override
    public void validate(int id) {
        if (id<0) {
            throw new ParkGarauException("Id cannot be less than 0 or 0");
        }
    }
    
    
}
