package th.co.erp.sme.model.request.auth;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import th.co.erp.sme.base.BaseRequest;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class LoginRequest extends BaseRequest {
    private String username;
    private String password;
}
