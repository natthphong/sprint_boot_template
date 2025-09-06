package th.co.erp.sme.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Primary
@Configuration
public class CustomConfigMapProperties {
    @Value("${app.application-name}")
    private String applicationName;
    @Value("${app.tokens.access-token.expired}")
    private Long accessTokenExp;
    @Value("${app.tokens.refresh-token.expired}")
    private Long refreshTokenExp;
    @Value("${app.tokens.jwt-secret}")
    private String jwtSecret;

}
