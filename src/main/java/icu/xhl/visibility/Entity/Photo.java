package icu.xhl.visibility.Entity;

import java.util.Date;

public class Photo {
    private int id;
    private String location;
    private String path;
    private int visibility;
    private Date date;

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getPath() {
        return path;
    }

    public int getVisibility() {
        return visibility;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
