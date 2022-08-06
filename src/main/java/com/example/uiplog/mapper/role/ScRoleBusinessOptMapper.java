package com.example.uiplog.mapper.role;


import com.example.uiplog.entity.log.ScBusinessOpt;
import com.weds.framework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao

public interface ScRoleBusinessOptMapper {
    List<ScBusinessOpt> getScRoleBusinessOptsByUserId(@Param("userId") Integer userId, @Param("client") String client);
}
