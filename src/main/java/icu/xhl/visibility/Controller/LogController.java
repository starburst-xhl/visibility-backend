package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Entity.Log;
import icu.xhl.visibility.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping
    public List<Log> getAllLogs() {
        return logService.selectAll();
    }
}
