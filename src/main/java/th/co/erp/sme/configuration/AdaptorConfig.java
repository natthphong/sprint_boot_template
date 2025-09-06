//package th.co.erp.sme.configuration;
//
//import okhttp3.OkHttpClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//import th.co.erp.sme.exception.handler.RestTemplateResponseErrorHandler;
//import th.co.erp.sme.model.property.TemplateAdaptorProperties;
//
//import java.time.Duration;
//
//@Configuration
//public class AdaptorConfig {
//
//
//
//    private final TemplateAdaptorProperties templateAdaptorProperties;
//
//    public AdaptorConfig(TemplateAdaptorProperties templateAdaptorProperties) {
//        this.templateAdaptorProperties = templateAdaptorProperties;
//    }
//
//    public OkHttp3ClientHttpRequestFactory clientHttpRequestFactory() {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .readTimeout(Duration.ofMillis(500))
//                .build();
//        return new OkHttp3ClientHttpRequestFactory(httpClient);
//    }
//
//
//    @Bean
//    @Qualifier("template")
//    public RestTemplate template(RestTemplateBuilder restTemplateBuilder) {
//        return restTemplateBuilder
//                .requestFactory(this::clientHttpRequestFactory)
//                .errorHandler(new RestTemplateResponseErrorHandler())
//                .setReadTimeout(Duration.ofMillis(templateAdaptorProperties.getDefaultReadTimeout()))
//                .setConnectTimeout(Duration.ofMillis(templateAdaptorProperties.getDefaultConnectTimeout()))
//                .build();
//    }
//}
