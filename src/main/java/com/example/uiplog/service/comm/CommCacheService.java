package com.example.uiplog.service.comm;

import com.alibaba.fastjson2.JSON;
import com.example.uiplog.constant.CommConstant;
import com.example.uiplog.entity.cache.UserCache;
import com.example.uiplog.entity.log.ScBusinessOpt;
import com.example.uiplog.entity.log.ScBusinessOptApiCache;
import com.example.uiplog.mapper.CommCacheMapper;
import com.example.uiplog.mapper.log.ScBusinessOptMapper;
import com.example.uiplog.mapper.role.ScRoleBusinessOptMapper;
import com.example.uiplog.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

import static com.example.uiplog.utils.RedisUtils.DAY_ONE_EXPIRE;


@Component
public class CommCacheService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CommCacheMapper commCacheMapper;
    @Autowired
    private ScRoleBusinessOptMapper scRoleBusinessOptMapper;
    @Autowired
    private ScBusinessOptMapper scBusinessOptMapper;

    /**
     * @Author: Yi
     * @param userId 学生id
     * @Description 读取学生缓存信息
     * @Date: 10:15 2021/4/8
     */
    public UserCache getUserInfo(Integer userId){
        return getAfterRefreshUserInfo(userId,true);
    }

    private UserCache getAfterRefreshUserInfo(Integer userId, Boolean refreshIfNotExist){
        if(userId==null || userId<=0)
            return null;
        String redisKey = CommConstant.REDIS_CACHE + ":USER:" + userId;
        String json = redisUtils.getString(redisKey);
        if(StringUtils.isBlank(json)){
            if(refreshIfNotExist){ //重新读库
                refreshUserInfo(userId);
                return getAfterRefreshUserInfo(userId,false);
            }
            return null;
        }
        return JSON.parseObject(json, UserCache.class);
    }

    public void refreshUserInfo(Integer userId){
        if(userId==null || userId<=0)
            return;
        String redisKey = CommConstant.REDIS_CACHE + ":USER:" + userId;
        UserCache userCache = commCacheMapper.getUserMainInfo(userId, File.separator + CommConstant.IMG_HEAD);
        if(userCache != null){
            userCache.setDormInfo(commCacheMapper.getUserDormInfo(userId));//获取宿舍资料
            userCache.setLeaveList(commCacheMapper.getUserLeaveInfo(userId));//获取未过期的有效请假
            redisUtils.set(redisKey,JSON.toJSONString(userCache),3 * DAY_ONE_EXPIRE); //缓存有效3天
        }else
            redisUtils.set(redisKey,"",1 * DAY_ONE_EXPIRE);
    }

    //获取api操作信息
    public ScBusinessOptApiCache getOptByApi(String apiName, Integer refreshIfNotExits) {
        String searchKey = CommConstant.REDIS_OPT + ":"+apiName;
        Object res = redisUtils.get(searchKey);
        if(res==null){
            if(refreshIfNotExits==null || 1==refreshIfNotExits) {
                refreshOptByApi(apiName);
                return getOptByApi(apiName,0);
            }
            return null;
        }
        return JSON.parseObject(res.toString(),ScBusinessOptApiCache.class);
    }

    //刷新api操作信息
    public void refreshOptByApi(String apiName) {
        String searchKey = CommConstant.REDIS_OPT + ":"+apiName;
        ScBusinessOptApiCache scBusinessOpt = scBusinessOptMapper.selectByOptApi(apiName);
        if (scBusinessOpt == null){
            redisUtils.delete(searchKey);
            return;
        }
        redisUtils.set(searchKey, JSON.toJSONString(scBusinessOpt));
    }

    //刷新用户操作权限（查库）
    public void refreshUserOpts(Integer userId, String client){
        String searchKey = CommConstant.REDIS_ONLINE_USER + ":"+userId+":opt:"+client;
        List<ScBusinessOpt> resultList = scRoleBusinessOptMapper.getScRoleBusinessOptsByUserId(userId,client);
        //每次存入数据时清空库里内容
        redisUtils.delete(searchKey);
        for(int i=0;i<resultList.size();i++){
            redisUtils.rightPush(searchKey,resultList.get(i).getOptNo());
        }
    }

     //获取用户操作权限（缓存）
    public List<String> getRedisUserOpts(Integer userId, String client, Integer refreshIfNotExits){
        String searchKey = CommConstant.REDIS_ONLINE_USER + ":"+userId+":opt:"+client;
        List<Object> list = redisUtils.range(searchKey);
        if(list==null || list.size()==0){
            if(refreshIfNotExits==null || 1==refreshIfNotExits) {
                refreshUserOpts(userId,client);
                return getRedisUserOpts(userId,client,0);
            }
            return null;
        }
        return (List<String>)(List)list;
    }
}
