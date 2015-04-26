package com.opdar.stbackground.common;

import com.opdar.framework.db.interfaces.EnumValue;

/**
 * Created by Jeffrey on 2015/4/26.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public enum  Configure implements EnumValue {
    PROJECT_NAME,SSDB_IP,SSDB_PWD,SSDB_PORT,CURRENT_THEME
    ;

    private String value;
    public String getValue() {
        return value;
    }

    public void setValue(String s) {
        value = s;
    }
}
