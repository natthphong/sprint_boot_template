package th.co.erp.sme.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> extends BaseResponse {
    private String code;

    private String title;
    private String message;
    private String description;
    private T body;


    public static ResponseModel<Void> success(){
        return ResponseModel.<Void>builder()
                .code("0000")
                .title("success")
                .description("success")
                .message("success")
                .body(null)
                .build();
    }

    public ResponseModel<T> successWithBody(T body){
        return ResponseModel.<T>builder()
                .code("0000")
                .title("success")
                .description("success")
                .message("success")
                .body(body)
                .build();
    }






}

