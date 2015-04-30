package com.opdar.stbackground.beans;

import java.util.HashMap;

/**
 * Created by Jeffrey on 2015/4/29.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class Tables {
    public static class Table{
        private Class<?> tableEntityCls;
        private String desc;

        public Class<?> getTableEntityCls() {
            return tableEntityCls;
        }

        public void setTableEntityCls(Class<?> tableEntityCls) {
            this.tableEntityCls = tableEntityCls;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public static HashMap<String,Table> tables = new HashMap<String, Table>();
    public static void put(String simpleName,Class<?> clz,String desc){
        Table table = new Table();
        table.tableEntityCls = clz;
        table.desc = desc;
        tables.put(simpleName,table);
    }

    public static Table get(String name){
        return tables.get(name);
    }
}
