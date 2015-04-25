package com.opdar.stbackground.controller;

import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;
import com.opdar.framework.web.views.HtmlView;
import com.opdar.stbackground.beans.TestBean;

/**
 * Created by Jeffrey on 2015/4/10.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller(value = "/",prefix = "html")
public class IndexController {

    @Router("index")
    public HtmlView routerRender(String testParam, TestBean bean){
        return new HtmlView("index.html");
    }
}
