/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import java.util.List;
import np.com.drose.parkgarau.modules.permission.Permission;
import np.com.drose.parkgarau.modules.user.UserType;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
public class Identity {

    public static final String SESSION_KEY = "login.security";
    private String userName; //LOGIN ID
    private String userFullName; //or FULL NAME
    private String companyCode;
    private String companyBranchCode;
    private UserType userType; // COMPANY or SYSTEM
    private List<Permission> permissions;
    private boolean firstTimeLogin;

    public Identity(String userName, String userFullName, String companyCode, String companyBranchCode, UserType userType, List<Permission> permissions, boolean firstTimeLogin) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.companyCode = companyCode;
        this.companyBranchCode = companyBranchCode;
        this.userType = userType;
        this.permissions = permissions;
        this.firstTimeLogin = firstTimeLogin;
    }

    public Identity() {
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public UserType getUserType() {
        return userType;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public boolean isFirstTimeLogin() {
        return firstTimeLogin;
    }

    @Override
    public String toString() {
        return "Identity{" + "userName=" + userName + ", userFullName=" + userFullName + ", companyCode=" + companyCode + ", companyBranchCode=" + companyBranchCode + ", userType=" + userType + ", permissions=" + permissions + ", firstTimeLogin=" + firstTimeLogin + '}';
    }

}
