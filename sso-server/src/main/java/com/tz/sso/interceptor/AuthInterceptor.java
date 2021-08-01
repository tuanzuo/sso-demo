package com.tz.sso.interceptor;

import com.tz.sso.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>TokenAuth拦截器</p>
 *
 * @version 1.3.0
 * @time 2020-08-29 13:50
 **/
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    private static final List<String> urlPreList = Arrays.asList("/login", "/doLogin");

    private static final String SSO_GRANT_TOKEN_URL = "/sso/grant/token";

    private String ssoHost = "www.sso.com:8081";
    private String ssoUrl = "http://www.sso.com:8081";
    private String tbHost = "www.tb.com";
    private String tbUrl = "http://www.tb.com:8082/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ssoToken = CookieUtils.getSsoToken(request);
        if (StringUtils.isNotBlank(ssoToken)) {
            String redirect = request.getParameter("redirect");
            String fromServer = request.getParameter("fromServer");
            if (StringUtils.isNotBlank(redirect) && StringUtils.isNotBlank(fromServer)) {
                response.sendRedirect(fromServer + "/sso/grant/token?onceToken=" + UUID.randomUUID().toString() + "&redirect=" + redirect);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
