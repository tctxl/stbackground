package com.opdar.stbackground.utils;

import com.alibaba.fastjson.JSON;
import com.opdar.stbackground.common.Configure;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Jeffrey on 2015/4/25.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class CacheUtils {
    static SSDB ssdb = null;

    static {
        ssdb = SSDBs.pool(Configure.SSDB_IP.getValue(), Integer.valueOf(Configure.SSDB_PORT.getValue()), 2000, null);
        if(!ssdb.ping().ok()){
            System.out.println("SSDB 连接失败");
        }
    }

    /**
     * @param key 键
     * @param value 值
     * @param timeout 超市时间（秒）
     */
    public static void cache(String key,Object value,int timeout){
        if(timeout == -1){
            ssdb.set(key,JSON.toJSONBytes(value));
        }else{
            ssdb.setx(key, JSON.toJSONBytes(value),timeout);
        }
    }

    public static Response getCache(String key){
        return ssdb.get(key);
    }

    public static Response del(String... key){
        return ssdb.multi_del(key);
    }

    public static boolean exist(String key){
        return ssdb.exists(key).asInt() > 0;
    }

    public static Response expire(String key,int timeout){
        return ssdb.expire(key, timeout);
    }

    public static <T>Object derializable(String content, Type type){
        if(type instanceof ParameterizedTypeImpl){
            if(List.class.isAssignableFrom(((ParameterizedTypeImpl) type).getRawType())){
                return JSON.parseArray(content, (Class<T>) ((ParameterizedTypeImpl) type).getActualTypeArguments()[0]);
            }
        }
        return JSON.parseObject(content, type);
    }

    public static void main(String[] args) {
        ssdb.flushdb("");
    }

}
