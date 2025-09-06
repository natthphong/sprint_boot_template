package th.co.erp.sme.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Try;

import java.util.TimeZone;

public class JsonHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    private JsonHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T jsonStringToObject(String jsonStringValue, Class<T> clazz) {
        return Try.of(() -> objectMapper.readValue(jsonStringValue, clazz))
                .getOrNull();
    }

    public static String objectToJsonString(Object object) {
        return Try.of(() -> objectMapper.writeValueAsString(object))
                .getOrNull();
    }

    public static String objectToJsonString(Object object, FilterProvider filterId) {
        return Try.of(() -> objectMapper.writer(filterId).writeValueAsString(object))
                .getOrNull();
    }

    public static <T> T jsonStringToObjectTypeRef(String jsonStringValue, TypeReference<T> typeReference) {
        return Try.of(() -> objectMapper.readValue(jsonStringValue, typeReference))
                .getOrNull();
    }

    public static <T> T objectToObject(Object object, Class<T> clazz) {
        return Try.of(() -> {
            String value = object instanceof String ? object.toString() : objectMapper.writeValueAsString(object);
            return objectMapper.readValue(value, clazz);
        }).getOrNull();
    }

    public static <T> T objectToObjectTypeRef(Object object, TypeReference<T> valueTypeRef) {
        return Try.of(() -> {
            String value = object instanceof String ? object.toString() : objectMapper.writeValueAsString(object);
            return objectMapper.readValue(value, valueTypeRef);
        }).getOrNull();
    }

}
