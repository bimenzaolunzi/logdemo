package com.itheima.logdemo.web;

import com.itheima.logdemo.utils.LogBean;
import com.itheima.logdemo.utils.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/")
@RestController
public class ApiController {
    private final static Logger logger = LoggerFactory.getLogger("kafka");
    @Autowired
    private ApiService apiService;

    @LogInfo
    @GetMapping("/test")
    public Object test() {
        LogBean logBean = LogBean.logBeanThreadLocal.get();
        logBean.setMessage("I am Controller");
        logger.info(logBean.toString());
        return apiService.test();
    }

    @LogInfo
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean ok = apiService.login(username, password);
        if (ok) {
            Cookie cookie = new Cookie("sid", username);
            //设置path，让所有请求均可以获取
            cookie.setPath("/");
            response.addCookie(cookie);
            return "success";
        } else {
            return "error";
        }
    }
}
