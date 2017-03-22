/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package np.com.drose.parkgarau.platform.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class PasswordEncryptor {
    public static String encode(String textToEncode){
        return Base64.encodeBase64String((byte[])DigestUtils.sha256((String)textToEncode));
    }
    public static boolean isEquals(String cypherText,String textToCheck){
        return cypherText.equals(PasswordEncryptor.encode(textToCheck));
    }
}
