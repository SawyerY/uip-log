package com.example.uiplog.mapper.log;

import com.example.uiplog.entity.log.ScOptLog;
import com.example.uiplog.entity.log.ScOptLogReq;
import com.weds.framework.core.annotation.MyBatisDao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ScOptLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScOptLog record);

    int insertSelective(ScOptLog record);

    List<ScOptLog> selectList(@Param("req") ScOptLogReq record, @Param("loginUserId") Integer loginUserId, @Param("client") String client);

    int updateByPrimaryKeySelective(ScOptLog record);

    int updateByPrimaryKey(ScOptLog record);

}
