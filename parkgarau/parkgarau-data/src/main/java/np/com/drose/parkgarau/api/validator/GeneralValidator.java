/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.api.validator;

import javax.ejb.Local;

/**
 *
 * @author bibek
 */
@Local
public interface GeneralValidator {
    public void validate(String code);
    public void validate(int id);
}
