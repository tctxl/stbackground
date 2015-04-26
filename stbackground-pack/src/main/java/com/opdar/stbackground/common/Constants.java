package com.opdar.stbackground.common;

/**
 * Created by Jeffrey on 2015/4/25.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class Constants {

    public static final class Cache{
        public static final Integer USER_TIMEOUT = 1800;//sec
        public static final Integer USER_RULE_TIMEOUT = 3600 * 24;//sec
    }

    public static final class CacheKey{
        public static final String USER(String token){
            return USER.concat(token);
        }

        public static final String USER_RULE(String userId){
            return USER_RULE.concat(userId);
        }

        private static final String USER = "USER`$`";
        private static final String USER_RULE = "USER_RULE`$`";

    }

}
