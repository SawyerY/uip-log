package com.example.uiplog.mapper;

import com.example.uiplog.entity.user.ScUser;
import com.weds.framework.core.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@MyBatisDao
public interface ScUserMapper {

    ScUser selectByPrimaryKey(Integer userId);

}
