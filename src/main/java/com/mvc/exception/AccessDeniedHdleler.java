package com.mvc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.response.ErrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
public class AccessDeniedHdleler implements AccessDeniedHandler {

    private final ObjectMapper mapper ;



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (ServletOutputStream out = response.getOutputStream()) {
            ErrResponse err = ErrResponse.error(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
            mapper.writeValue(out, err);
            System.out.println("ACCESSDINED EXCEPTION");
        } catch (Exception e) {

        }
    }
}
