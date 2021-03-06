package com.opdar.stbackground.beans.tables;

import com.opdar.framework.db.anotations.Field;
import com.opdar.stbackground.annotation.Desc;

/**
 * Created by Jeffrey on 2015/4/27.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Desc("配置信息管理")
public class ConfigureEntity {
    @Field("_id")
    private String id;
    @Desc("名称")
    private String configureName;
    @Desc("值")
    private String configureValue;
    @Desc("描述")
    private String description ;
    @Desc("版本")
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigureName() {
        return configureName;
    }

    public void setConfigureName(String configureName) {
        this.configureName = configureName;
    }

    public String getConfigureValue() {
        return configureValue;
    }

    public void setConfigureValue(String configureValue) {
        this.configureValue = configureValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}