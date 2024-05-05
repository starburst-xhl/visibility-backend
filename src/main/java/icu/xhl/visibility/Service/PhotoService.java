package icu.xhl.visibility.Service;

import icu.xhl.visibility.Mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public Resource getPhotoResource(int id) throws MalformedURLException {
        String photoUrl = getPhotoUrl(id);
        return new UrlResource(photoUrl);
    }
}
