package np.com.drose.parkgarau.utils;

import com.google.gson.Gson;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class JSONUtils {

    public static String objectToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Object jsonToObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Object.class);
    }
}
