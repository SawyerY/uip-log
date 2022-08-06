package com.example.uiplog.entity.log;

import com.weds.framework.core.common.model.OrderBy;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class ScOptLogReq extends OrderBy {

    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "1.管理员日志 2.个人日志")
    private Integer tabId;
    @ApiModelProperty(required = true, value = "开始时间")
    private String beginTime;
    @ApiModelProperty(required = true, value = "结束时间")
    private String endTime;
    @ApiModelProperty(required = true, value = "操作编号")
    private String optNo;

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOptNo() {
        return optNo;
    }

    public void setOptNo(String optNo) {
        this.optNo = optNo;
    }

    @Override
    public Map<String, String> getOrderByFieldMap() {
        return null;
    }
}
