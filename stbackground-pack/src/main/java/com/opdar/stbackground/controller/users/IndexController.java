package com.opdar.stbackground.controller.users;

import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.interfaces.View;
import com.opdar.framework.web.views.RedirectView;
import com.opdar.stbackground.auth.AuthManagement;
import com.opdar.stbackground.customs.BeetlView;

/**
 * Created by Jeffrey on 2015/4/10.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/",prefix = "html")
public class IndexController {

    @Router("index")
    public View routerRender(){
        if(AuthManagement.checkAuth()){
            return new RedirectView("/admin");
        }
        return new BeetlView("index.html",null);
    }

}
