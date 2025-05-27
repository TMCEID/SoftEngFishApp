package model;

import javax.swing.*;

public class User {
    private final int    userId;
    private final String phone;
    private final String username;
    //private ImageIcon avatar;

    private final char gender;                // 'M' or 'F'

    public User(int id, String phone, String username, String password, char gender) {
        this.userId   = id;
        this.phone    = phone;
        this.username = username;
        this.gender   = gender;
    }

    //getters
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
