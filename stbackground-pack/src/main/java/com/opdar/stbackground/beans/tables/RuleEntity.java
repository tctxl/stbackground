package com.opdar.stbackground.beans.tables;

import com.opdar.framework.db.anotations.Factor;
import com.opdar.framework.db.anotations.Field;
import com.opdar.stbackground.annotation.Desc;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 2015/4/26.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Desc("权限管理")
public class RuleEntity {
    @Field("_ID")
    @Desc("权限ID")
    private Integer id;
    @Field("RULE_NAME")
    @Desc("名称")
    private String name;
    @Field("RULE_DESCRIPTION")
    @Desc("描述")
    private String description;
    @Field("RULE_PARENT")
    @Desc("父级权限ID")
    private Integer parent;
    @Field("RULE_ICON")
    @Desc("图标")
    private String icon;
    @Field("RULE_TYPE")
    @Desc("最后一项")
    private Integer type;
    @Field("RULE_ACTION")
    @Desc("行为")
    private String action;
    private String accessory;
    private String accessoryName;
    @Desc("模块")
    private String module;
    @Desc("有效")
    private Integer disable;
    @Desc("级别")
    private String level;

    /**
     * 可用@Factor注解实现级联查询，但不推荐使用
     * 例：@Factor(value = "SELECT *FROM T_RULE TR WHERE #{id} = TR.RULE_PARENT AND TR._ID <> TR.RULE_PARENT ORDER BY TR.SEQ ASC",cls=RuleEntity.class)
     * 就能查出子级菜单啦
     */
    @Factor(value = "SELECT *FROM T_RULE TR LEFT JOIN T_RULE_LEVEL TRL ON TRL.RULE_ID = TR._ID LEFT JOIN T_LEVEL TL ON TL._ID = TRL.LEVEL_ID WHERE TL. LEVEL <= #{level} AND #{id} = TR.RULE_PARENT AND TR._ID <> TR.RULE_PARENT AND TR. DISABLE = '0' ORDER BY TR.SEQ ASC",cls=RuleEntity.class)
    private ArrayList<RuleEntity> rules = new ArrayList<RuleEntity>();

    public ArrayList<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(ArrayList<RuleEntity> rules) {
        this.rules = rules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
