package th.co.erp.sme.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.model.request.auth.LoginRequest;
import th.co.erp.sme.service.auth.LoginService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;


    @GetMapping("/guest")
//    @PreAuthorize("hasAnyRole('GUEST')")
    public String testPermissionController(){
        return "HELLO";
    }

    @PostMapping("/login")
    public BaseResponse loginService(@RequestBody @Valid LoginRequest req){
        return loginService.execute(req);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String testPermissionController2(){
        return "HELLO ADMIN";
    }
}
