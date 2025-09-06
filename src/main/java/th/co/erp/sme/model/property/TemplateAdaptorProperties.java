package th.co.erp.sme.model.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "integration.template")
public class TemplateAdaptorProperties {

    private String baseUrl;
    private Integer defaultReadTimeout;
    private Integer defaultConnectTimeout;
    private Outbound outbound;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Outbound {
        private String createTxn;
    }
}
