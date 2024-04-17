package icu.xhl.visibility;

import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private Timestamp createat;
    private int phonenbr;
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreateat() {
        return createat;
    }

    public void setCreateat(Timestamp createat) {
        this.createat = createat;
    }

    public int getPhonenbr() {
        return phonenbr;
    }

    public void setPhonenbr(int phonenbr) {
        this.phonenbr = phonenbr;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
