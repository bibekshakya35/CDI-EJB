/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.modules.parkingmanagement;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public enum ParkingUnit {
    MINUTES(1),
    HALFHOUR(30),
    HOUR(60);
    
   private long timeInMinute;

    private ParkingUnit(long timeInMinute) {
        this.timeInMinute = timeInMinute;
    }
   
    
    
}
