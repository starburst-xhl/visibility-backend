package icu.xhl.visibility.Mapper;

import icu.xhl.visibility.Entity.Photo;
import icu.xhl.visibility.Entity.PhotoStatusDispense;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PhotoMapper {
    String getPhotoUrl(int id);
    List<PhotoStatusDispense> getPhotoStatusDispense();
    List<Photo> getPhotosByDateRange(Map<String, Date> dateRange);
    int addPhoto(Photo photo);
    int dispensePhoto(int photoid,int userid);
    List<Photo> getPhotosByUser(int userid);
    int addPhotoStatus(int photoid,int status, int new_visibility, String location, Date update_at);
    void passPhoto(int photoid,Date update_at,int new_visibility);
    void doubleCheckPhoto(int photoid,Date update_at);
    Photo getPhotoById(int id);
}
