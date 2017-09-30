import de.codecentric.boot.admin.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhenghuasheng on 2017/9/30.
 */
@SpringBootApplication
@ComponentScan("com")
@EnableAdminServer
public class AdminApplication {
    private static Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        logger.info("============= Application Start Success =============");
    }
}
