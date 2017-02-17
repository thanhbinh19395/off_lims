package hcmue.gst.off.configuration;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * Created by Ho Phuong on 14/02/2017.
 */
@Configuration
public class ThymeleafConfig {
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
