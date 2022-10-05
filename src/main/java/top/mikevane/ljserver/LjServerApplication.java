package top.mikevane.ljserver;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("top.mikevane.ljserver.mapper")
public class LjServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjServerApplication.class, args);
    }

}
