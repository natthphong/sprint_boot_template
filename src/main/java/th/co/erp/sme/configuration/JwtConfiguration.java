package th.co.erp.sme.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import th.co.erp.sme.property.CustomConfigMapProperties;
import th.co.erp.sme.util.JwtHelper;

import java.util.Base64;

@Configuration
public class JwtConfiguration {

    @Bean
    public JwtHelper jwtHelper(CustomConfigMapProperties customConfigMapProperties) {
        return new JwtHelper(customConfigMapProperties.getApplicationName(),
                Base64.getEncoder().encodeToString(customConfigMapProperties.getJwtSecret().getBytes()));
    }
}
