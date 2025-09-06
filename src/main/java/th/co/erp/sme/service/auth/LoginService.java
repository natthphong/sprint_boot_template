package th.co.erp.sme.service.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.exception.model.BusinessException;
import th.co.erp.sme.model.entity.EmployeeEntity;
import th.co.erp.sme.model.jwt.UserJwtPayload;
import th.co.erp.sme.model.request.auth.LoginRequest;
import th.co.erp.sme.model.response.LoginResponse;
import th.co.erp.sme.property.CustomConfigMapProperties;
import th.co.erp.sme.repository.EmployeeRepository;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.util.JwtHelper;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements BaseService<LoginRequest, ResponseModel<LoginResponse>> {


    private final EmployeeRepository employeeRepository;
    private final CustomConfigMapProperties customConfigMapProperties;
    private final JwtHelper jwtHelper;

    @Override
    public ResponseModel<LoginResponse> execute(LoginRequest req) {

        EmployeeEntity emp = employeeRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "LG1001"));

        UserJwtPayload jwtPayload = UserJwtPayload
                .builder()
                .branchCode(emp.getDefaultBranchCode())
                .role("")
                .companyCode(emp.getCompanyCode())
                .isActive(emp.getIsActive())
                .permissions(new ArrayList<>())
                .employeeId(emp.getId())
                .build();
        String accessToken = jwtHelper.generateAppToken(emp.getUsername(), JsonHelper.objectToJsonString(jwtPayload), new ArrayList<>(), customConfigMapProperties.getAccessTokenExp());
        String refreshToken = jwtHelper.generateAppToken(emp.getUsername(), new ArrayList<>(), customConfigMapProperties.getRefreshTokenExp());


        return ResponseModel
                .<LoginResponse>builder()
                .body(
                        LoginResponse
                                .builder()
                                .accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .jwtPayload(jwtPayload)
                                .build()
                )
                .build();
    }
}
