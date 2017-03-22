/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.user.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author bibekshakya
 */
@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = User.FIND_ALL, query = "Select u from User u"),
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "Select u from User u where u.userName =:userName"),
    @NamedQuery(name = User.FIND_BY_USER_AND_PASSWORD,query = "Select u from User u where u.userName =:userName and u.password=:password")
})
@Entity
@Table(name = "tbl_users")
@XmlRootElement
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    
    @NotNull
    @Column(name = "user_fullname")
    private String fullname;
    
    @NotNull
    @Size(min = 10,max = 20)
    @Column(name = "user_name")
    private String userName;
    
    @NotNull
    @Column(name = "email_address")
    private String emailAddress;
    
    @NotNull
    @Column(name = "password")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public UserROLE roles=UserROLE.USER;
    
    public static final String PREFIX="np.com.drose.data.modules.user.domain.";
    
    public static final String FIND_ALL=PREFIX+"FIND_ALL";
    
    public static final String FIND_BY_USERNAME=PREFIX+"FIND_BY_USER_NAME";
    
    public static final String FIND_BY_USER_AND_PASSWORD=PREFIX+"FIND_BY_USER_AND_PASSWORD";
    
    public enum UserROLE{
        ADMIN,USER,GUEST,OPERATOR;
        public static String getRoles(UserROLE roles){
            switch(roles){
                case ADMIN:
                    return "ADMIN";
                case USER:
                    return "USER";
                case GUEST:
                    return "GUEST";
                default:
                    return null;
            }
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserROLE getRoles() {
        return roles;
    }

    public void setRoles(UserROLE roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public User() {
    }
    
}
