package th.co.erp.sme.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String code;
    private String title;
    private String message;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object additionalInfo;
}

