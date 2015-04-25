package com.opdar.stbackground.controller;

import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.views.HtmlView;
import com.opdar.stbackground.beans.TestBean;
import com.opdar.stbackground.beans.UserEntity;

import java.util.List;

/**
 * Created by Jeffrey on 2015/4/19.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/login/")
public class LoginController {

    @Router("index.html")
    public HtmlView index(String test1,TestBean test) {
        return new HtmlView("index.html");
    }

    @Router(value = "users.auth")
    public List<UserEntity> findUser(String username, String password) {
        IDao<UserEntity> dao = Context.get(BaseDatabase.class).getDao(UserEntity.class);
        List<UserEntity> array = dao.SELECT().END().findAll();
        return array;
    }
}