package com.opdar.stbackground.interceptor;

/**
 * Created by Jeffrey on 2015/4/24.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class CommonInterceptor {
    public void before(){
        System.out.println("auth check");
    }
    public void after(){
        System.out.println("auth complate！");
    }
}
