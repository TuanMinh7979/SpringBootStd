package com.mvc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.response.ErrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
public class AuthenticationExceptionHdleler implements AuthenticationEntryPoint {
    private final ObjectMapper mapper ;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (ServletOutputStream out = response.getOutputStream()) {
            ErrResponse err = ErrResponse.error(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
            System.out.println("AUTHENTICATION EXCEPTION");
            mapper.writeValue(out, err);
        } catch (Exception e) {

        }
    }
}
