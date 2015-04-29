package com.opdar.stbackground.beans;

import java.util.HashMap;

/**
 * Created by Jeffrey on 2015/4/29.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class Tables {
    public static HashMap<String,Class<?>> tables = new HashMap<String, Class<?>>();
    public static void put(String simpleName,Class<?> clz){
        tables.put(simpleName,clz);
    }

    public static Class<?> get(String name){
        return tables.get(name);
    }
}
