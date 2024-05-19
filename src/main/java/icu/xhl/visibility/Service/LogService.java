package icu.xhl.visibility.Service;

import icu.xhl.visibility.Entity.Log;
import icu.xhl.visibility.Mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="logService")
public class LogService implements LogMapper {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }
}
