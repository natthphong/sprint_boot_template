package th.co.erp.sme.controller.rest;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.model.jwt.UserJwtPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    @GetMapping("/me")
    public BaseResponse getAllPermission(){
        List<GrantedAuthority> authorities = new ArrayList<>(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        );
        UserJwtPayload jwtPayload = (UserJwtPayload)SecurityContextHolder.getContext().getAuthentication().getCredentials();
//        List<String> permissions =
        Map<String, List<String>> permissionsMap = new HashMap<>();
        permissionsMap.put(jwtPayload.getRole(), authorities.stream().map(GrantedAuthority::getAuthority).toList());

        return ResponseModel
                .builder()
                .body(permissionsMap)
                .build();
    }
}
