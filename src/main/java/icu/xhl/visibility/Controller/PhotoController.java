package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Entity.Photo;
import icu.xhl.visibility.Entity.PhotoStatusDispense;
import icu.xhl.visibility.Entity.User;
import icu.xhl.visibility.Mapper.UserMapper;
import icu.xhl.visibility.Service.PhotoService;
import icu.xhl.visibility.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam int id) throws IOException {
        Resource photo = photoService.getPhotoResource(id);
        byte[] photoBytes = StreamUtils.copyToByteArray(photo.getInputStream());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getFilename() + "\"")
                .body(photoBytes);
    }

    @GetMapping("/all")
    public List<PhotoStatusDispense> getAllPhotos(@RequestHeader(value = "Authorization") String token) {
        if (TokenUtil.verify(token)){
            return photoService.getPhotoStatusDispense();
        }
        else {
            return null;
        }
    }

    @GetMapping("/timeRange")
    public List<Photo> getPhotosByTimeRange(@RequestHeader(value = "Authorization")String token,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate) throws ParseException {
        if (TokenUtil.verify(token)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            return photoService.getPhotoByDateRange(start, end);
        }
        else {
            return null;
        }
    }

    @PostMapping("/add")
    public void addPhoto(@RequestBody MultipartFile photo,
                         @RequestHeader(value = "Authorization") String key,
                         @RequestParam int visibility,
                         @RequestParam String location) throws IOException {
        if (key.equals("123456")){
            Date date = new Date();
            photoService.insertPhoto(photo, visibility, location, date);
        }

    }

    @GetMapping("/dispense")
    public Map<String,Object> dispensePhoto(@RequestHeader(value = "Authorization") String token,
                             @RequestParam String resolution){
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verifyManager(token)){
            List<User> users= userMapper.selectByRole("emploee");
            System.out.println(users);
            List<PhotoStatusDispense> photos = photoService.getPhotoStatusDispense();
            int index = 0;
            int length = users.size();
            for(PhotoStatusDispense photo: photos){
                if(photo.getUserid() == 0){
                    photoService.dispensePhoto(photo.getId(), users.get(index).getId());
                    photoService.addPhotoStatus(photo.getId(),0, photo.getVisibility(), photo.getLocation(), new Date());
                    index = (index+1)%length;
                }
            }
            map.put("code",200);
            map.put("message","success");
        }
        else {
            map.put("code",403);
            map.put("message","forbidden");
        }
        return map;
    }

    @GetMapping("/getPhotos")
    public List<Photo> getPhotos(@RequestHeader(value = "Authorization") String token){
        System.out.println(token);
        if (TokenUtil.verify(token)){
            String username = TokenUtil.getUsername(token);
            User user = userMapper.selectByUsername(username, token);
            return photoService.getPhotosByUser(user.getId());
        }
        else {
            return null;
        }
    }

    @PostMapping("/pass")
    public Map<String,Object> passPhoto(@RequestHeader(value = "Authorization") String token,
                          @RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verify(token)){
            photoService.passPhoto(id,new Date(),photoService.getPhotoById(id).getVisibility());
            map.put("code",200);
        }
        else {
            map.put("code",403);
        }
        return map;
    }

    @PostMapping("/doubleCheck")
    public Map<String,Object> doubleCheck(@RequestHeader(value = "Authorization") String token,
                            @RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verify(token)){
            photoService.doubleCheckPhoto(id,new Date());
            map.put("code",200);
        }
        else {
            map.put("code",403);
        }
        return map;
    }
}