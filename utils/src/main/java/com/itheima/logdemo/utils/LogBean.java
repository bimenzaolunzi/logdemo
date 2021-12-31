package com.itheima.logdemo.utils;

import com.alibaba.fastjson.JSON;

public class LogBean {
    private String rid, sid, tid, from, message;
    private String ip;
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public final static ThreadLocal<LogBean> logBeanThreadLocal = new ThreadLocal<>();

    public LogBean(String rid, String sid, String tid, String from, String message) {
        this.rid = rid;
        this.sid = sid;
        this.tid = tid;
        this.from = from;
        this.message = message;
        logBeanThreadLocal.set(this);
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;


    public String getRid() {
        return rid;
    }

    public String getSid() {
        return sid;
    }

    public String getTid() {
        return tid;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        this.setTime(System.currentTimeMillis());
        return JSON.toJSONString(this);
    }
}
