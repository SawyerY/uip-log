package com.example.uiplog.entity.cache;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserLeaveInfo {
    private Integer leaveId;
    private Integer leaveTypeId;
    private String leaveTypeName;
    private String leaveTowards;
    private String leaveRemark;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer actUserId;
    private String actUserName;
    private Timestamp actDate;
    private String actRemark;
}
