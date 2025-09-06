package th.co.erp.sme.util;


import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import th.co.erp.sme.util.JsonHelper;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionStoreUtils {

//    private final RedisTemplate<String,String> redisTemplate;
//    private  ValueOperations<String,String> valueOperations  ;
//
//    private  HashOperations<String,String,String> hashOperations;

//    @PostConstruct
//    public void init(){
//        valueOperations  =  redisTemplate.opsForValue();
//        hashOperations = redisTemplate.opsForHash();
//    }

    public void set(String key , String value , Duration exp){
//        if (Objects.isNull(exp)){
//            valueOperations.set(key,value);
//        }else{
//            valueOperations.set(key,value,exp);
//        }
    }

    public void hSetAll(String key , Map<String,String> value){
//        this.hashOperations.putAll(key, value);
    }
    public <T>List<T> hGetAll(String key) {
        List<T> result = new ArrayList<>();
//        Map<Object, Object> body = this.redisTemplate.boundHashOps(key).entries();
//
//        List<Map.Entry<Object, Object>> entryList = new ArrayList<>(body.entrySet());
//        entryList =  entryList.stream().sorted(Comparator.comparing(entry -> entry.getKey().toString())).toList();
//        for (Map.Entry<Object, Object> entry : entryList) {
//            result.add(JsonHelper.jsonStringToObjectTypeRef(entry.getValue().toString(), new TypeReference<>() {}));
//        }
        return result;
    }

    public void hSet(String key,String field,String value){
        log.info("set key {} field {}" , key, field);
//        hashOperations.put(key,field,value);
    }

    public <T> Optional<T> hGet(String key,String field){
//        String value =   hashOperations.get(key,field);
        String value =   "";
        return StringUtils.isBlank(value)
                ?Optional.empty()
                :Optional.of(JsonHelper.jsonStringToObjectTypeRef(value, new TypeReference<>() {}));
    }

    public void hDelete(String key,String field){
//        hashOperations.delete(key,field);
    }

    public void delete(String key){
//        redisTemplate.delete(key);
    }
    public <T> Optional<T> get(String key){
//        String value =   valueOperations.get(key);
        String value =   "";
        return StringUtils.isBlank(value)
                ?Optional.empty()
                :Optional.of(JsonHelper.jsonStringToObjectTypeRef(value, new TypeReference<>() {}));
    }

}
