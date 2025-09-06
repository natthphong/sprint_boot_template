package th.co.erp.sme.filter;


import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import th.co.erp.sme.model.error.ErrorResponse;
import th.co.erp.sme.model.jwt.UserJwtPayload;
import th.co.erp.sme.property.CustomConfigMapProperties;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.util.JwtHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final th.co.erp.sme.property.CustomConfigMapProperties customConfigMapProperties;
    private final JwtHelper jwtHelper;

    public JwtAuthenticationFilter(CustomConfigMapProperties customConfigMapProperties, JwtHelper jwtHelper) {
        this.customConfigMapProperties = customConfigMapProperties;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String requestPath = request.getServletPath();
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        if (requestPath.contains("/api/v1/auth")) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(-1,-1, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            unauthorized(response);
            return;
        }
        String jwtToken = authHeader.substring(7);
        try {

            Jws<Claims> res = jwtHelper.verifyToken(jwtToken);
            UserJwtPayload jwtPayload = JsonHelper.objectToObjectTypeRef(res.getPayload().get("body"), new TypeReference<>() {});

            jwtPayload.getPermissions().forEach(e -> authorities.add(new SimpleGrantedAuthority(e)));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(jwtPayload.getEmployeeId(), jwtPayload, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());
            unauthorized(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    public void unauthorized(HttpServletResponse response) {
        String jsonError = JsonHelper.objectToJsonString(ErrorResponse.builder()
                .code("66666")
                .title(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .description(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getWriter().write(jsonError);
        } catch (Exception ignore) {

        }

    }


}
