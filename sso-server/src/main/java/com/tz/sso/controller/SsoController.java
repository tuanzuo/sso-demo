package com.tz.sso.controller;

import com.tz.sso.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class SsoController {
    private static Logger log = LoggerFactory.getLogger(SsoController.class);

    private static final String USER_TOKEN_FLAG = "userToken";

    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse response, ModelMap model) throws IOException {
        model.put("redirect", req.getParameter("redirect"));
        model.put("fromServer", req.getParameter("fromServer"));
        return "login";
    }

    @RequestMapping("/doLogin")
    public void doLogin(HttpServletRequest req, HttpServletResponse response, ModelMap model) throws IOException {
        Cookie cookie = new Cookie(CookieUtils.SSO_TOKEN_FLAG, "sso-" + UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setDomain("www.sso.com");
        response.addCookie(cookie);

        String redirect = req.getParameter("redirect");
        String fromServer = req.getParameter("fromServer");
        response.sendRedirect(fromServer + "/sso/grant/token?onceToken=" + UUID.randomUUID().toString() + "&redirect=" + redirect);
    }

}