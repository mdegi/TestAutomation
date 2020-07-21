package mt.com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:conf/security.properties")
public class AppConfig {

    @Value("${websiteUser}")
    private String websiteUserName;

    @Value("${websitePwd}")
    private String websitePassword;

    @Bean
    public Credentials getAppCredentials() {
        return new Credentials(websiteUserName, websitePassword);
    }

}
