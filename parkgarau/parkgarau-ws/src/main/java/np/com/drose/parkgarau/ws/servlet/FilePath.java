/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.ws.servlet;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public enum FilePath {
    IMAGEPATH("/opt/parkgarau/img");
    private final String text;

    private FilePath(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
    
    
    
}
