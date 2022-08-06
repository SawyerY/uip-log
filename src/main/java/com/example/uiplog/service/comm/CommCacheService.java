package com.example.uiplog.service.comm;

import com.alibaba.fastjson2.JSON;
import com.example.uiplog.constant.CommConstant;
import com.example.uiplog.entity.log.ScBusinessOptApiCache;
import com.example.uiplog.mapper.log.ScBusinessOptMapper;
import com.example.uiplog.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommCacheService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ScBusinessOptMapper scBusinessOptMapper;

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

}
