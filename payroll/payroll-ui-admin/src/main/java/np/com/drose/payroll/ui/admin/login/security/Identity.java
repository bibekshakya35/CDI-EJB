/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.ui.admin.login.security;

/**
 *
 * @author bibekshakya
 */
public class Identity {
    public static final String SESSION_KEY="login.security";
    private final String username;
    private final String authkey;
    private final boolean login;
    private final String userfullname;
    private final String userType;
    
    public static Identity createDefaultInstance(){
        return new Identity("", "", false, "", "");
    }

    public Identity(String username, String authkey, boolean login, String userfullname, String userType) {
        this.username = username;
        this.authkey = authkey;
        this.login = login;
        this.userfullname = userfullname;
        this.userType = userType;
    }

    public static String getSESSION_KEY() {
        return SESSION_KEY;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthkey() {
        return authkey;
    }

    public boolean isLogin() {
        return login;
    }

    public String getUserfullname() {
        return userfullname;
    }

    public String getUserType() {
        return userType;
    }
    
    
}
