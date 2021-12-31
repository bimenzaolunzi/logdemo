package com.itheima.logdemo.user;

import com.itheima.logdemo.utils.LogBean;
import com.itheima.logdemo.utils.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger("kafka");
    @LogInfo
    @RequestMapping("/info")
    public String info(){
        LogBean logBean = LogBean.logBeanThreadLocal.get();
        logBean.setMessage("I am user.controller");
        logger.info(logBean.toString());
        return "zhangsan";
    }
}
