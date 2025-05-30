package model;

import javax.swing.*;

public class User {

    private String profileImagePath;
    private String phone;
    private String role = "Customer";
    private final int    userId;

    private  String username;
    //private ImageIcon avatar;

    private final char gender;                // 'M' or 'F'

    public User(int id, String phone, String username, String password, char gender) {
        this.userId   = id;
        this.phone    = phone;
        this.username = username;
        this.gender   = gender;
    }

    //getters

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public char      getGender()   { return gender; }
    public String    getPhone()    { return phone;  }
    public String    getUsername() { return username; }
    /** primary-key of table user */
    public int getUserId() {
        return userId;
        }

    // avatar helper
    public javax.swing.ImageIcon getAvatar() {
        String path = gender == 'F'
                ? "/avatar woman - 3.png"
                : "/avatar man - 2.png";
        return new javax.swing.ImageIcon(getClass().getResource(path));
    }

}
