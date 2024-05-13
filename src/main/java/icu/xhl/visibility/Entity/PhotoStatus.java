package icu.xhl.visibility.Entity;

import java.util.Date;

public class PhotoStatus {
    private int photoid;
    private int status;
    private Date update_at;
    private int new_visibility;

    public int getPhotoid() {
        return photoid;
    }

    public void setPhotoid(int photoid) {
        this.photoid = photoid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getNew_visibility() {
        return new_visibility;
    }

    public void setNew_visibility(int new_visibility) {
        this.new_visibility = new_visibility;
    }
}
