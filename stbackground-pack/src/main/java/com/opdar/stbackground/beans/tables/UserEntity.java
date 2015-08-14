package com.opdar.stbackground.beans.tables;

import com.opdar.framework.db.anotations.Field;
import com.opdar.framework.db.anotations.Table;
import com.opdar.stbackground.annotation.Desc;

import java.sql.Timestamp;

/**
 * Created by Jeffrey on 2015/4/23.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Table("test.t_users")
@Desc("用户管理")
public class UserEntity {

    @Field("_id")
    @Desc("主键ID")
    private String tid;
    @Desc("用户名")
    private String userName;
    @Desc("密码")
    private String userPwd;
    @Desc("昵称")
    private String nickName;
    @Desc("头像")
    private String headImg;
    @Desc("联系方式")
    private String contact;
    private Integer levelId;
    private Integer level;
    private String groupMask;
    @Desc("最后登陆时间")
    private Timestamp lastLoginTime;
    private Integer proportion;
    @Desc("有效")
    private Integer hidein;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGroupMask() {
        return groupMask;
    }

    public void setGroupMask(String groupMask) {
        this.groupMask = groupMask;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getHidein() {
        return hidein;
    }

    public void setHidein(Integer hidein) {
        this.hidein = hidein;
    }
}
