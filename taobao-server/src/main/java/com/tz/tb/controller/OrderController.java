package com.tz.tb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    private static Logger log = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/create")
    public String create(HttpServletRequest req, HttpServletResponse response, ModelMap model) throws IOException {
        model.put("createOrderResult", String.format("%s%s", "订单创建成功，订单号：", UUID.randomUUID().toString()));
        return "index";
    }

}