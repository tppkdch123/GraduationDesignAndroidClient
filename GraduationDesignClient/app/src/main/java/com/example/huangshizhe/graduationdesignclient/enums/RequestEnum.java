package com.example.huangshizhe.graduationdesignclient.enums;

public enum RequestEnum {
    LOGIN("/api/user/loginByPassword","GET","密码登录"),;

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    private String url;

    private String type;

    RequestEnum(String url, String type, String desc) {
        this.url = url;
        this.type = type;
        this.desc = desc;
    }

    private String desc;
}
