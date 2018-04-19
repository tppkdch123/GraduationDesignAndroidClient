package com.example.huangshizhe.graduationdesignclient.enums;

public enum RequestEnum {
    LOGIN_PASSWORD("/api/user/loginByPassword","GET","密码登录"),
    AUTO_LOGIN("/api/user/auto-login","GET","自动登录"),
    SEND_EMAIL("/api/user/sendLoginEmail","GET","发送登录验证码"),
    SEND_EMAIL_REGISTER("/api/user/sendRegisterEmail","GET","发送注册验证码"),
    REGISTER("/api/user/register","GET","注册"),
    LOGIN_VER("/api/user/login","GET","验证码登录"),
    ;

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
