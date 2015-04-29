package com.opdar.stbackground.customs;

import com.alibaba.fastjson.JSON;
import com.opdar.framework.utils.Utils;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.interfaces.View;
import com.opdar.stbackground.beans.PackageJSON;
import com.opdar.stbackground.common.Configure;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2015/4/25.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class SystemBeetlView implements View {
    private static GroupTemplate gt;
    String contentType = "text/html";
    public static GroupTemplate getGroupTemplate(){
        if(gt == null){
            String path = System.getProperty("seed.root");
            FileResourceLoader resourceLoader = new FileResourceLoader(new File(path,"WEB-INF/system/").getAbsolutePath(),"utf-8");
            Configuration cfg = null;
            try {
                cfg = Configuration.defaultConfiguration();
                gt = new GroupTemplate(resourceLoader, cfg);
                Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("projectName",Configure.PROJECT_NAME.getValue());
                vars.put("baseurl",Configure.BASE_URL.getValue());

                gt.setSharedVars(vars);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gt;
    }

    String templatePath = null;
    Map<String,Object> dataModels = null;
    public SystemBeetlView(String templatePath, Map<String, Object> dataModels){
        this.templatePath = templatePath;
        this.dataModels = dataModels;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> headers() {
        return null;
    }

    public byte[] renderView() {
        Template t = getGroupTemplate().getTemplate(templatePath);
        if(dataModels != null)
        t.binding(dataModels);
        try {
            return t.render().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public String contentType() {
        return contentType;
    }

    public int getCode() {
        return 200;
    }
}
