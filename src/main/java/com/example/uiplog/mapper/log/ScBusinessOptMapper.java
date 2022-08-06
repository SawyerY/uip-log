package com.example.uiplog.mapper.log;

import com.example.uiplog.entity.log.ScBusinessOptApiCache;
import com.weds.framework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface ScBusinessOptMapper {
    ScBusinessOptApiCache selectByOptApi(@Param("optApi") String optApi);
}
