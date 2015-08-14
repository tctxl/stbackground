package com.opdar.stbackground.utils;

import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.impl.FieldModel;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.interfaces.View;
import com.opdar.stbackground.annotation.Desc;
import com.opdar.stbackground.customs.SystemBeetlView;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by Jeffrey on 2015/4/26.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class Utils {
    public static Map<String, Object> productDataModels(String key, Object value){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(key, value);
        return map;
    }
    public static View executeTableView(Class<?> tableClz){
        BaseDatabase database = Context.get(BaseDatabase.class);
        IDao<?> dao = database.getDao(tableClz);
        List<?> datas = dao.SELECT().END().findAll();
        Map<String, FieldModel> map = dao.getFieldNames();
        return Utils.executeTableView(map,datas);
    }

    public static View executeTableView(Map<String, FieldModel> map,Object datas){
        List<String> fields = new LinkedList<String>();
        List<String> pNames = new LinkedList<String>();
        List<String> descs = new LinkedList<String>();
        for (Iterator<Map.Entry<String, FieldModel>> it = map.entrySet().iterator();it.hasNext();){
            Map.Entry<String, FieldModel> entry = it.next();
            Field field= entry.getValue().getField();
            Desc desc = field.getAnnotation(Desc.class);
            if(desc != null){
                fields.add(entry.getValue().getMapping());
                pNames.add(entry.getKey());
                descs.add(desc.value());
            }
        }
        Map<String, Object> result = Utils.productDataModels("tables",datas);
        result.put("fields",fields);
        result.put("pNames",pNames);
        result.put("descs",descs);
        return new SystemBeetlView("tables.html",result);
    }

    public final static String md5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
