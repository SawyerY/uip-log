package com.example.uiplog.mapper;

import com.example.uiplog.entity.user.ScUser;
import com.weds.framework.core.annotation.MyBatisDao;



@MyBatisDao
public interface ScUserMapper {

    ScUser selectByPrimaryKey(Integer userId);

}
