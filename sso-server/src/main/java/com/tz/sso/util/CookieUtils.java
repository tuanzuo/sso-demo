package com.tz.sso.util;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author tuanzuo
 * @version 1.7.0
 * @time 2021-08-01 16:59
 **/
public class CookieUtils {

    public static final String USER_TOKEN_FLAG = "userToken";
    public static final String SSO_TOKEN_FLAG = "ssoToken";

    public static String getUserToken(HttpServletRequest req) {
        return getString(req, USER_TOKEN_FLAG);
    }

    public static String getSsoToken(HttpServletRequest req) {
        return getString(req, SSO_TOKEN_FLAG);
    }

    private static String getString(HttpServletRequest req, String tokenFlag) {
        String token = null;
        Cookie[] cookies = req.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (tokenFlag.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
