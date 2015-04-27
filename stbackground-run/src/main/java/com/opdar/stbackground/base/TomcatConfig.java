package com.opdar.stbackground.base;

import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.interfaces.EnumValue;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.server.base.DefaultConfig;
import com.opdar.framework.utils.Utils;
import com.opdar.framework.web.common.Context;
import com.opdar.stbackground.common.Configure;
import com.opdar.stbackground.utils.CacheUtils;

import java.util.Properties;

/**
 * Created by Jeffrey on 2015/4/22.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class TomcatConfig extends DefaultConfig {

    Properties properties = new Properties();

    public TomcatConfig(){
        load("/com/opdar/stbackground/base/config.properties");
    }

    public void load(String propertiesFile){
        try {
            properties.load(TomcatConfig.class.getResourceAsStream(propertiesFile));
            setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        BaseDatabase database = Context.get(BaseDatabase.class);
        IDao<Configure> dao = database.getDao(Configure.class);
        dao.SELECT().END();
    }

    @Override
    public void onDestory() {
        System.out.println("ondestory");
    }
}
