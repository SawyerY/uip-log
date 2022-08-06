package com.example.uiplog.service.comm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import com.example.uiplog.entity.log.ScBusinessOptApiCache;
import com.example.uiplog.entity.log.ScOptLog;
import com.example.uiplog.entity.user.ScUser;
import com.example.uiplog.mapper.ScUserMapper;
import com.example.uiplog.mapper.log.ScOptLogMapper;
import com.example.uiplog.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志相关方法
 */
@Slf4j
@Service
public class CommOptLogService {

    @Autowired
    private ScOptLogMapper scOptLogMapper;
    @Autowired
    private CommCacheService cacheService;
    @Autowired
    private ScUserMapper scUserMapper;

    /**
     * @param loginUserId 操作人
     * @param optNo       操作编号
     * @param optApi      操作api接口
     * @param logDesc     描述语句
     * @Author: Yi
     * @Description 需要替换解析参数的日志描述执行该方法
     * @Date: 10:41 2019/1/24
     */
    public String makeDesc(Integer loginUserId, String optNo, String logDesc, String optApi,
                           Map<String, String> paramGet, JSONArray paramPost) {

        ScUser loginUser = scUserMapper.selectByPrimaryKey(loginUserId);
        //截取字符串获得接口名称
        switch (optNo) {
            case "OPT01SC0023":
                return logDesc.replace("{0}", loginUser.getUserName());
            case "OPT02SC0023":
                return logDesc.replace("{0}", getBody(paramPost, 0, "userName"));
            case "OPT03SC0023":
                return logDesc.replace("{0}", getBody(paramPost, 0, "userName"));
            case "OPT05SC0023":
                return logDesc.replace("{0}", loginUser.getUserName());
            default://缺省
                return logDesc;
        }
    }

    /**
     * @param o
     * @Author: Yi
     * @Description 执行日志写库
     * @Date: 10:42 2019/1/24
     */
    public void sysLogInsert(ScOptLog o) {
        try {
            ScBusinessOptApiCache scBusinessOpt = cacheService.getOptByApi(o.getOptApi(), 1);
            if (scBusinessOpt == null)
                return;
            o.setOptName(scBusinessOpt.getOptName()); //操作名称
            o.setOptNo(scBusinessOpt.getOptNo()); //操作编号
            o.setOptTypeId(scBusinessOpt.getOptTypeId()); //操作类别

            Map<String, String> m = o.getParamsUrl() == null ? new HashMap<>() : JsonUtil.paramToMap(o.getParamsUrl());
            o.setCtRm(m.containsKey("rm") ? m.get("rm") : "");
            o.setCtCal(m.containsKey("cal") ? m.get("cal") : "");
            if ("POST".equals(o.getRequestMethod())) {
                JSONArray bodyArr = JSON.parseArray(o.getParamsBody());
                o.setParamsBody(bodyArr.toString());
                o.setLogDesc(makeDesc(o.getCtUserId(), o.getOptNo(), scBusinessOpt.getLogDescSql(), o.getOptApi(), m, bodyArr));
            } else if ("GET".equals(o.getRequestMethod())) {
                o.setParamsBody(null);
                o.setLogDesc(makeDesc(o.getCtUserId(), o.getOptNo(), scBusinessOpt.getLogDescSql(), o.getOptApi(), m, null));
            }

            scOptLogMapper.insert(o);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作日志入库失败： "+o.getOptApi());
        }
    }


    //获取参数中：指定对象的指定属性值
    public String getBody(JSONArray paramPost, int arrNum, String key) {
        return JSON.parseObject(paramPost.get(arrNum).toString()).getString(key);
    }

}
