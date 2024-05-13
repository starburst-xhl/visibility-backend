package icu.xhl.visibility.Util;

import icu.xhl.visibility.Mapper.SettingMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingUtil {

    @Autowired
    private SettingMapper settingMapper;

    private static SettingUtil settingUtil;

    @PostConstruct
    public void init() {
        settingUtil = this;
        settingUtil.settingMapper = this.settingMapper;
    }

    public static String getSetting(String key) {
        return settingUtil.settingMapper.getSetting(key);
    }

    public static int setSetting(String key, String value) {
        return settingUtil.settingMapper.setSetting(key, value);
    }
}
