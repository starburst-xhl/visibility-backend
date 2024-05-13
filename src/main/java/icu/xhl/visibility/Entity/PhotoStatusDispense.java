package icu.xhl.visibility.Entity;

import java.util.Date;

public class PhotoStatusDispense {
    private int id;
    private int status;
    private int userid;
    private String location;
    private Date date;
    private int visibility;
    private int new_visibility;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getNew_visibility() {
        return new_visibility;
    }

    public void setNew_visibility(int new_visibility) {
        this.new_visibility = new_visibility;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
