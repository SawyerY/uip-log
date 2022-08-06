package com.example.uiplog.entity.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ScBusinessOpt {
    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "功能操作id")
    private Integer optId;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "功能操作编号")
    private String optNo;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "功能操作名称")
    private String optName;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "所属业务模块")
    private String businessNo;
    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "操作类型: 1.查询 2.新增 3.修改 4.删除 5.审核通过 6.审核拒绝")
    private Integer optTypeId;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "日志描述构建语句")
    private String logDescSql;
    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "0.停用 1.使用")
    private Integer useStatusId;
    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "排序")
    private Integer showOrder;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "服务系统版本")
    private String version;
    @ApiModelProperty(required = true, dataType = "string", example = "1", value = "备注")
    private String remark;
    @ApiModelProperty(required = true, dataType = "string", value = "所属菜单")
    private String menuBusinessId;
    @ApiModelProperty(required = true, dataType = "int", example = "1", value = "配置状态")
    private Integer configStatus;

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public String getOptNo() {
        return optNo;
    }

    public void setOptNo(String optNo) {
        this.optNo = optNo == null ? null : optNo.trim();
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName == null ? null : optName.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public Integer getOptTypeId() {
        return optTypeId;
    }

    public void setOptTypeId(Integer optTypeId) {
        this.optTypeId = optTypeId;
    }

    public String getLogDescSql() {
        return logDescSql;
    }

    public void setLogDescSql(String logDescSql) {
        this.logDescSql = logDescSql == null ? null : logDescSql.trim();
    }

    public Integer getUseStatusId() {
        return useStatusId;
    }

    public void setUseStatusId(Integer useStatusId) {
        this.useStatusId = useStatusId;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMenuBusinessId() {
        return menuBusinessId;
    }

    public void setMenuBusinessId(String menuBusinessId) {
        this.menuBusinessId = menuBusinessId;
    }

    public Integer getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(Integer configStatus) {
        this.configStatus = configStatus;
    }
}
