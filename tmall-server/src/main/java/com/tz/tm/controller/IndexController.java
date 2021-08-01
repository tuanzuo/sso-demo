package com.tz.tm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpSession session, ModelMap model, Long userId, String userName) {
		return "index";
	}
}
