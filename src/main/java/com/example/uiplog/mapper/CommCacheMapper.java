package com.example.uiplog.mapper;

import com.example.uiplog.entity.cache.UserCache;
import com.example.uiplog.entity.cache.UserDormInfo;
import com.example.uiplog.entity.cache.UserLeaveInfo;
import com.weds.framework.core.annotation.MyBatisDao;

import org.apache.ibatis.annotations.Param;

import java.util.List;


@MyBatisDao
public interface CommCacheMapper {

	/**
	* @Author: Yi
	* @Description 获取学生个人资料
	* @Date: 16:49 2021/4/7
	*/
	UserCache getUserMainInfo(@Param("userId") Integer userId, @Param("imgPath") String imgPath);

	/**
	 * @Author: Yi
	 * @Description 获取学生请假信息
	 * @Date: 16:49 2021/4/7
	 */
	List<UserLeaveInfo> getUserLeaveInfo(@Param("userId") Integer userId);

	/**
	 * @Author: Yi
	 * @Description 获取学生宿舍信息
	 * @Date: 16:49 2021/4/7
	 */
	UserDormInfo getUserDormInfo(@Param("userId") Integer userId);
}
