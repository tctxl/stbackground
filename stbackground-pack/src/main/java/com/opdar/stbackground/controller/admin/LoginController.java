package com.opdar.stbackground.controller.admin;

import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.impl.Join;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.views.RedirectView;
import com.opdar.stbackground.auth.AuthManagement;
import com.opdar.stbackground.beans.tables.UserEntity;
import com.opdar.stbackground.common.MapperFilter;
import com.opdar.stbackground.utils.Utils;

/**
 * Created by Jeffrey on 2015/4/19.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/login/")
public class LoginController {

    @Router(value = "users.auth")
    public RedirectView findUser(String username, String password,String rememberMe) {
        BaseDatabase database = Context.get(BaseDatabase.class);
        IDao<UserEntity> userEntityIDao = database.getDao(UserEntity.class);
        String simpleName = userEntityIDao.getSimpleTableName(UserEntity.class);
        userEntityIDao.setFilter(MapperFilter.USER).addMapper("TL.LEVEL AS LEVEL");
        password = Utils.md5(password);
        UserEntity user = userEntityIDao.addMapper("TL.LEVEL AS LEVEL").SELECT()
                .JOIN(Join.LEFT, "t_level TL", String.format("%s.LEVEL_ID = TL._ID", simpleName))
                .WHERE("USER_NAME", username)
                .AND()
                .IS("USER_PWD", password)
                .WhereEND().END().findOne();
        if(user != null){
            AuthManagement.login(user);
            return new RedirectView("/dashboard");
        }
        return new RedirectView("/admin/index.html");
    }

    @Router
    public RedirectView logout(){
        AuthManagement.logout();
        return new RedirectView("/admin/index.html");
    }
}