package com.opdar.stbackground.common;

import com.opdar.framework.db.impl.MappingFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jeffrey on 2015/4/26.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public enum MapperFilter implements MappingFilter {
    LOGIN("LEVEL",null),RULE("RULES",null)
    ;
    private HashMap<String,String> redefined = new HashMap<String, String>();
    private HashSet<String> filter = new HashSet<String>();
    MapperFilter(String filter,String redefine){
        if(filter!=null){
            String[] s = filter.split(",");
            List<String> list=Arrays.asList(s);
            this.filter.addAll(list);
        }
        if(redefine!=null)
            redefined.putAll(com.opdar.framework.utils.Utils.spliteParams(redefine));
    }

    public HashSet<String> getFilter() {
        return filter;
    }

    public HashMap<String, String> getRedefinItionField() {
        return redefined;
    }
}
