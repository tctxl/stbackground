package com.opdar.stbackground.controller.users;

import com.alibaba.fastjson.TypeReference;
import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.interfaces.View;
import com.opdar.framework.web.views.RedirectView;
import com.opdar.stbackground.auth.AuthManagement;
import com.opdar.stbackground.beans.tables.ConfigureEntity;
import com.opdar.stbackground.customs.BeetlView;
import com.opdar.stbackground.utils.CacheUtils;

import java.util.List;

/**
 * Created by Jeffrey on 2015/4/10.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/",prefix = "html")
public class IndexController {

    @Router("index")
    public View index(){
        if(AuthManagement.checkAuth()){
            return new RedirectView("/admin");
        }
        return new BeetlView("index.html",null);
    }

    @Router("test")
    public List<ConfigureEntity> test(){
        List<ConfigureEntity> result = null;

        if(!CacheUtils.exist("CONFIGURE")){
            BaseDatabase database = Context.get(BaseDatabase.class);
            IDao<ConfigureEntity> configs = database.getDao(ConfigureEntity.class);
            result = configs.SELECT().END().findAll();
            CacheUtils.cache("CONFIGURE",result,600);
        }else{
            result = (List<ConfigureEntity>) CacheUtils.derializable(CacheUtils.getCache("CONFIGURE").asString(),new TypeReference<List<ConfigureEntity>>(){}.getType());
        }
        return result;
    }
}
