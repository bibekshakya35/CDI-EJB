/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.utills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bibekshakya
 */
public class DateFormatter {

    public static String getdate(String data) {
        try {
            SimpleDateFormat formatnow = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
            SimpleDateFormat formatneeded = new SimpleDateFormat("EEE dd, MMM, yyyy");
            Date date1 = formatnow.parse(data);
            String date2 = formatneeded.format(date1);

            return date2;

        } catch (ParseException ex) {
            ex.printStackTrace();
            return data;
        }

    }

    public static Date getDate(String date) {
        try {
            SimpleDateFormat formatnow = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
            Date date1 = formatnow.parse(date);
            return date1;
        } catch (ParseException ex) {
            Logger.getLogger(DateFormatter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
