package com.example.uiplog.entity.cache;

import lombok.Data;

import java.util.List;

@Data
public class UserCache {
    //档案
    private Integer userId;
    private Integer userType;
    private String userName;
    private String userNo;
    private String userPhone;
    private String userPhoto;
    private Integer status;
    //组织部门
    private Integer classId;
    private String className;
    private Integer instituteId;
    private String instituteName;
    private Integer majorId;
    private String majorName;
    private Integer gradeId;
    private String gradeName;
    //辅导员
    private Integer headteacherId;
    private String headteacherName;
    private String headteacherNo;
    private String headteacherPhone;
    //宿舍相关
    private UserDormInfo dormInfo;
    //请假相关（已生效且未过期）
    private List<UserLeaveInfo> leaveList;

}
