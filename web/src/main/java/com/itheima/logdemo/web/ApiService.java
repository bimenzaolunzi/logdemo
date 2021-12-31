package com.itheima.logdemo.web;

import com.itheima.logdemo.utils.LogBean;
import com.itheima.logdemo.utils.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final static Logger logger = LoggerFactory.getLogger("kafka");
    @Autowired
    RestTemplate restTemplate;

    @LogInfo
    public Object test() {


        LogBean logBean = LogBean.logBeanThreadLocal.get();
        logBean.setMessage("I am service");
        logger.info(logBean.toString());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logBean.setMessage("before call user");
        logger.info(logBean.toString());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = restTemplate.getForObject("http://user/info", String.class);
        logBean.setMessage("after call user, res=" + res);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return logBean.toString();
    }

    @LogInfo
    public boolean login(String username, String password) {
        return restTemplate.getForObject("http://user/check?username=" + username + "&password=" + password, boolean.class);
    }
}
