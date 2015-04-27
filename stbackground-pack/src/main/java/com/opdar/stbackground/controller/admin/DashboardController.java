package com.opdar.stbackground.controller.admin;

import com.opdar.framework.db.impl.BaseDaoImpl;
import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.impl.FieldModel;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.web.anotations.Before;
import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Injection;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.interfaces.View;
import com.opdar.framework.web.views.HtmlView;
import com.opdar.framework.web.views.RedirectView;
import com.opdar.stbackground.beans.ConfigureEntity;
import com.opdar.stbackground.beans.RuleEntity;
import com.opdar.stbackground.beans.UserEntity;
import com.opdar.stbackground.common.Configure;
import com.opdar.stbackground.common.MapperFilter;
import com.opdar.stbackground.customs.BeetlView;
import com.opdar.stbackground.auth.AuthInterceptor;
import com.opdar.stbackground.auth.AuthManagement;
import com.opdar.stbackground.customs.SystemBeetlView;
import com.opdar.stbackground.services.DashboardService;
import com.opdar.stbackground.utils.Utils;

import java.util.*;

/**
 * Created by Jeffrey on 2015/4/25.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/dashboard",prefix = "html")
@Before(AuthInterceptor.class)
public class DashboardController {

    @Router
    public View index(){
        return new SystemBeetlView("admin.html",null);
    }

    @Router
    public View menus(){
        List<RuleEntity> rules = null;
        View view = null;
        if((rules = AuthManagement.getUserRule()) != null){
            Map<String, Object> dataModel = Utils.productDataModels("rules", rules);
            view = new SystemBeetlView("menus.html",dataModel);
        }
        return view;
    }

    @Router
    public View configure(){
        return Utils.executeTableView(ConfigureEntity.class);
    }

    @Router
    public View rules(){
        return Utils.executeTableView(RuleEntity.class);
    }

    @Router
    public View users(){
        BaseDatabase database = Context.get(BaseDatabase.class);
        IDao<?> dao = database.getDao(UserEntity.class);
        List<?> datas = dao.setFilter(MapperFilter.LOGIN).SELECT().END().findAll();
        Map<String, FieldModel> map = dao.getFieldNames();
        return Utils.executeTableView(map,datas);
    }
}
