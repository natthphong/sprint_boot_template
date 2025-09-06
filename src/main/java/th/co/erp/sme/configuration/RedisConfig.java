//package th.co.erp.sme.configuration;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import th.co.erp.sme.model.property.CacheProperties;
//
//@Configuration
//public class RedisConfig {
//
//
//
//    private final CacheProperties cacheProperties;
//
//    public RedisConfig(CacheProperties cacheProperties) {
//        this.cacheProperties = cacheProperties;
//    }
//
//    @Bean("redisConnection")
//    public RedisConnectionFactory redisConnectionMessage() {
//        var clientConfigBuilder = LettuceClientConfiguration.builder();
//        var configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(cacheProperties.getRedisHost());
//        configuration.setPort(Integer.parseInt(cacheProperties.getRedisPort()));
//        configuration.setDatabase(cacheProperties.getRedisConfig().getDb());
//        if (StringUtils.isNotBlank(cacheProperties.getPassword())) {
//            configuration.setPassword(cacheProperties.getPassword());
//        }
//        return new LettuceConnectionFactory(configuration, clientConfigBuilder.build());
//    }
//
//    @Bean("redisTemplate")
//    public RedisTemplate<String, String> redisTemplateMessage(@Qualifier("redisConnection") RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//
//}