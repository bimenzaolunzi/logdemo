package com.itheima.logdemo.utils;

import com.alibaba.fastjson.JSON;

public class LogBean {
    private String rid, sid, tid, from, message;

    public LogBean(String rid, String sid, String tid, String from, String message) {
        this.rid = rid;
        this.sid = sid;
        this.tid = tid;
        this.from = from;
        this.message = message;
    }

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
        return JSON.toJSONString(this);
    }
}
