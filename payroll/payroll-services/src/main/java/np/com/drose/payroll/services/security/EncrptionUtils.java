/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author bibekshakya
 */
public class EncrptionUtils {

    // Advanced Encryption Standard (AES)
    private static final String ALGO ="AES";
    private static final byte[] KEYVALUE=new byte[]{'P','A','Y','r','O','l','L','b','i','b','n','i','r','o','S','k'};
    
    public static String encrypt(String password)throws Exception{
        Key key =getKeyGeneration();
        Cipher cipher =Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrptVal = cipher.doFinal(password.getBytes());
        return new BASE64Encoder().encode(encrptVal);
    }
    
    public static Key getKeyGeneration() throws Exception{
        Key key =new SecretKeySpec(KEYVALUE, ALGO);
        return key;
    }
    
//    private static final String ALGO = "AES";
//    private static final byte[] keyValue
//            = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't',
//                'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
//
//    public static String encrypt(String Data) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encVal = c.doFinal(Data.getBytes());
//        String encryptedValue = new BASE64Encoder().encode(encVal);
//        return encryptedValue;
//    }
//
//    public static String decrypt(String encryptedData) throws Exception {
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGO);
//        c.init(Cipher.DECRYPT_MODE, key);
//        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
//        byte[] decValue = c.doFinal(decordedValue);
//        String decryptedValue = new String(decValue);
//        return decryptedValue;
//    }
//
//    private static Key generateKey() throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        return key;
//    }

}
