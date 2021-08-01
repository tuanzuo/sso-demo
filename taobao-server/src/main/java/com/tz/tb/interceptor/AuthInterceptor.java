package com.tz.tb.interceptor;

import com.tz.tb.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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

    private static final List<String> urlPreList = Arrays.asList("/order");

    private static final String SSO_GRANT_TOKEN_URL = "/sso/grant/token";

    private String ssoHost = "www.sso.com:8081";
    private String ssoUrl = "http://www.sso.com:8081";
    private String tbHost = "www.tb.com";
    private String tbUrl = "http://www.tb.com:8082/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains(SSO_GRANT_TOKEN_URL)) {
            String onceToken = request.getParameter("onceToken");
            String redirect = request.getParameter("redirect");
            //FIXME 1、通过一次性token得到正式token
            String userToken = "u-" + UUID.randomUUID().toString();
            //FIXME 2、把正式token写入到cookie
            Cookie cookie = new Cookie(CookieUtils.USER_TOKEN_FLAG, userToken);
            cookie.setPath("/");
            cookie.setDomain(tbHost);
            response.addCookie(cookie);
            response.sendRedirect(redirect);
            return false;
        }

        String userToken = CookieUtils.getUserToken(request);
        for (String urlPre : urlPreList) {
            if (requestURI.contains(urlPre) && StringUtils.isBlank(userToken)) {
                response.sendRedirect(ssoUrl + "/login?redirect=" + tbUrl+"&fromServer="+tbUrl);
                return false;
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
