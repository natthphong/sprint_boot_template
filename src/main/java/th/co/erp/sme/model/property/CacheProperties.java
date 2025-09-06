package th.co.erp.sme.model.property;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CacheProperties {

    @Value("${REDIS_HOST}")
    private String redisHost;
    @Value("${REDIS_PORT}")
    private String redisPort;
    @Value("${REDIS_AUTH}")
    private String password;


    @Autowired
    private Gson gson;



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedisConfig {
        private String name;
        private Integer db;
        private Long ttl;
    }


}
