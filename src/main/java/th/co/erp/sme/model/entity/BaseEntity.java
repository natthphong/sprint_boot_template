package th.co.erp.sme.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

import java.io.IOException;


@MappedSuperclass
public class BaseEntity<T> {

    @JsonIgnore
    @Transient
    protected T previousState;

    @SuppressWarnings("unchecked")
    @PostLoad
    private void setPreviousState() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String serialized = mapper.writeValueAsString(this);
        this.previousState = (T) mapper.readValue(serialized, this.getClass());
    }

    public T getPreviousState(){
        return previousState;
    }

}
