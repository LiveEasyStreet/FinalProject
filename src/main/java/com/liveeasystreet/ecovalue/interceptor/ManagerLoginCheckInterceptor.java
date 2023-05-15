package com.liveeasystreet.ecovalue.interceptor;

import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ManagerLoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 매니저 로그인 인터셉터
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.MANAGER_LOGIN) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/manager/login?redirectURL=" + requestURI);

            return false;
        }

        log.info("REQUEST [{}][{}][{}]", session.getId(), requestURI, handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        log.info("RESPONSE [{}][{}][{}]", session.getId(), requestURI, handler);

        if (ex != null) {
            log.info("afterCompletion error!!", ex);
        }
    }
}