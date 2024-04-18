package icu.xhl.visibility;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("icu.xhl.visibility.Mapper")
public class VisibilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisibilityApplication.class, args);
    }

}
