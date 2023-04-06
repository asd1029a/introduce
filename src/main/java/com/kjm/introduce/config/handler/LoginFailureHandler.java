package com.kjm.introduce.config.handler;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginFailureHandler.java
 * Class 설명을 작성하세요.
 *
 * @author 김정민
 * @since 2023.04.06
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception instanceof AuthenticationServiceException) {
            request.setAttribute("LoginFail", "시스템 에러.");
        } else if (exception instanceof BadCredentialsException) {
            request.setAttribute("LoginFail", "아이디 또는 비밀번호가 틀렸습니다.");
        } else if (exception instanceof DisabledException) {
            request.setAttribute("LoginFail", "사용 불가한 아이디입니다.");
        } else if (exception instanceof LockedException) {
            request.setAttribute("LoginFail", "잠긴 아이디입니다.");
        } else if(exception instanceof AccountExpiredException) {
            request.setAttribute("LoginFailMessage", "이미 만료된 계정입니다.");
        } else if(exception instanceof CredentialsExpiredException) {
            request.setAttribute("LoginFailMessage", "비밀번호가 만료된 계정입니다.");
        } else request.setAttribute("LoginFailMessage", "계정을 찾을 수 없습니다.");

        response.sendRedirect("/login/error");
    }
}
