package com.opdar.stbackground.auth;

import com.alibaba.fastjson.TypeReference;
import com.opdar.framework.db.impl.BaseDatabase;
import com.opdar.framework.db.impl.BaseWhere;
import com.opdar.framework.db.impl.Join;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.db.interfaces.IWhere;
import com.opdar.framework.web.SeedWeb;
import com.opdar.framework.web.common.Context;
import com.opdar.framework.web.common.ISession;
import com.opdar.stbackground.beans.RuleEntity;
import com.opdar.stbackground.beans.UserEntity;
import com.opdar.stbackground.common.Constants;
import com.opdar.stbackground.common.MapperFilter;
import com.opdar.stbackground.utils.CacheUtils;
import org.nutz.ssdb4j.spi.Response;

import java.util.List;
import java.util.UUID;

/**
 * Created by Jeffrey on 2015/4/26.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class AuthManagement {

    public static String getToken(){
        ISession session = SeedWeb.SharedRequest().get().getSession();
        Object token = session.getValue("stoken");
        return token!=null?token.toString():null;
    }

    public static boolean checkAuth(){
        String token = getToken();
        if(token != null){
            boolean response = CacheUtils.exist(Constants.CacheKey.USER(token.toString()));
            return response;
        }
        return false;
    }

    public static UserEntity getUser(){
        String token = getToken();
        if(token != null) {
            Response response = CacheUtils.getCache(Constants.CacheKey.USER(token.toString()));
            if(response.ok()){
                return CacheUtils.derializable(response.asString(), UserEntity.class);
            }
        }
        return null;
    }

    public static List<RuleEntity> getUserRule(){
        UserEntity userEntity = getUser();
        if(userEntity != null){
            Response response = CacheUtils.getCache(Constants.CacheKey.USER_RULE(userEntity.getTid()));
            if(response.ok()){
                return CacheUtils.derializable(response.asString(), new TypeReference<List<RuleEntity>>(){}.getType());
            }else{
                return rebuildUserRule();
            }
        }
        return null;
    }

    public static void login(Object user){
        String stoken = UUID.randomUUID().toString();
        CacheUtils.cache(Constants.CacheKey.USER(stoken), user, Constants.Cache.USER_TIMEOUT);
        SeedWeb.SharedRequest().get().getSession().setValue("stoken",stoken);
        rebuildUserRule();
    }

    public static List<RuleEntity> rebuildUserRule(){
        UserEntity userEntity = getUser();
        if(userEntity != null){
            BaseDatabase database = Context.get(BaseDatabase.class);
            IDao<RuleEntity> dao = database.getDao(RuleEntity.class);
            String simpleName = dao.getSimpleTableName(RuleEntity.class);
            IWhere where = new BaseWhere();
            where.LTIS("TL.LEVEL", String.valueOf(userEntity.getLevel()));
            where.AND().IS(simpleName.concat("._ID"), "${" .concat(simpleName).concat(".RULE_PARENT").concat("}"));
            where.AND().IS(simpleName + ".DISABLE", "0");
            dao.setFilter(MapperFilter.RULE).SELECT()
                    .JOIN(Join.LEFT,"T_RULE_LEVEL TRL",String.format("TRL.RULE_ID = %s._ID",simpleName))
                    .JOIN(Join.LEFT,"T_LEVEL TL","TL._ID = TRL.LEVEL_ID")
                    .WHERE(where).ORDERBY(new BaseWhere.Order("SEQ", BaseWhere.Order.OrderType.ASC)).WhereEND().END();
            List<RuleEntity> rules = dao.findAll();
            CacheUtils.cache(Constants.CacheKey.USER_RULE(userEntity.getTid()),rules,Constants.Cache.USER_RULE_TIMEOUT);
            return rules;
        }
        return null;
    }

    public static boolean logout(){
        String token = getToken();
        if(token != null) {
            Response response = CacheUtils.del(Constants.CacheKey.USER(token));
            if(response.ok() && response.asInt() > 0){
                return true;
            }
        }
        return false;
    }

}
