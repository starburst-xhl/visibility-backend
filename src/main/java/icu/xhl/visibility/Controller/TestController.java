package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Entity.PhotoStatusDispense;
import icu.xhl.visibility.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    @ResponseBody
    public List<PhotoStatusDispense> test(@RequestParam String key) {
        return photoService.getPhotoStatusDispense();
    }
}
