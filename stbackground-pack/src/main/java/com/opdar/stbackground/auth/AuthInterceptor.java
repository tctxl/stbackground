package com.opdar.stbackground.auth;

import com.opdar.framework.web.views.RedirectView;
import com.opdar.stbackground.common.Constants;
import com.opdar.stbackground.utils.CacheUtils;

/**
 * Created by Jeffrey on 2015/4/24.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class AuthInterceptor {

    public Object before(){
        if(AuthManagement.checkAuth()){
            CacheUtils.expire(Constants.CacheKey.USER(AuthManagement.getToken()),Constants.Cache.USER_TIMEOUT);
            return true;
        }
        return new RedirectView("/admin/index.html");
    }

    public void after(){
    }
}
