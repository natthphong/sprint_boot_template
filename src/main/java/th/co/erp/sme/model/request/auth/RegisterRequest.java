package th.co.erp.sme.model.request.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import th.co.erp.sme.base.BaseRequest;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RegisterRequest extends BaseRequest {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String companyCode;

    private String branchCode;

    @NotNull
    @NotBlank
    private String firstNameTh;

    @NotNull
    @NotBlank
    private String lastNameTh;

    private String firstNameEn;
    private String lastNameEn;

    @NotNull
    @NotBlank
    private String employmentTypeCode; // from param group drop drawn

    @NotNull
    private LocalDate hireDate;

}
