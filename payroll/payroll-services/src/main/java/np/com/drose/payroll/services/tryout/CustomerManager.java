/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.tryout;

import javax.ejb.Stateless;

/**
 *
 * @author bibekshakya
 */
@Stateless
public class CustomerManager {
    public int getCustomerCount(){return 27;}
    public int getCustomerByRegion(String region){
        if (region.equalsIgnoreCase("east")) {
            return 10;
        }
        else if(region.equalsIgnoreCase("west")){
            return 50;
        }
        else{
            return 21;
        }
        
    }
}
