package icu.xhl.visibility.Service;

import icu.xhl.visibility.Entity.Photo;
import icu.xhl.visibility.Entity.PhotoStatusDispense;
import icu.xhl.visibility.Mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="photoService")
public class PhotoService implements PhotoMapper {
    @Autowired
    private PhotoMapper photoMapper;

    @Value("${spring.resources.static-locations}")
    private String contextPath;


    @Override
    public String getPhotoUrl(int id) {
        String photoPath = "/photos/" + photoMapper.getPhotoUrl(id);
        String diskPath = "D:/projects/visibility_data" + photoPath;
        if (Files.exists(Paths.get(diskPath))) {
            return "file:" + diskPath;
        } else {
            return "classpath:/static" + photoPath;
        }
    }

    @Override
    public List<PhotoStatusDispense> getPhotoStatusDispense() {
        return photoMapper.getPhotoStatusDispense();
    }

    @Override
    public List<Photo> getPhotosByDateRange(Map<String, Date> dateRange) {
        return photoMapper.getPhotosByDateRange(dateRange);
    }

    @Override
    public int addPhoto(Photo photo) {
        return photoMapper.addPhoto(photo);
    }

    @Override
    public int dispensePhoto(int photoid, int userid) {
        return photoMapper.dispensePhoto(photoid, userid);
    }

    @Override
    public int addPhotoStatus(int photoid,int status, int new_visibility, String location, Date update_at) {
        return photoMapper.addPhotoStatus(photoid,status, new_visibility, location, update_at);
    }

    @Override
    public void passPhoto(int photoid, Date update_at, int new_visibility) {
        photoMapper.passPhoto(photoid, update_at, new_visibility);
    }

    @Override
    public List<Photo> getPhotosByUser(int userid) {
        return photoMapper.getPhotosByUser(userid);
    }

    @Override
    public void doubleCheckPhoto(int photoid, Date update_at) {
        photoMapper.doubleCheckPhoto(photoid, update_at);
    }

    @Override
    public Photo getPhotoById(int id) {
        return photoMapper.getPhotoById(id);
    }

    public Resource getPhotoResource(int id) throws MalformedURLException {
        String photoUrl = getPhotoUrl(id);
        return new UrlResource(photoUrl);
    }

    public List<Photo> getPhotoByDateRange(Date startDate, Date endDate) {
        return getPhotosByDateRange(Map.of("startDate", startDate, "endDate", endDate));
    }

    public void insertPhoto(MultipartFile photo, int visibility,String location, Date date) throws IOException {
        Photo newPhoto = new Photo();
        Files.write(Paths.get("D:/projects/visibility_data/photos/" + photo.getOriginalFilename()), photo.getBytes());
        newPhoto.setPath(photo.getOriginalFilename());
        newPhoto.setVisibility(visibility);
        newPhoto.setLocation(location);
        newPhoto.setDate(date);
        addPhoto(newPhoto);
    }
}
