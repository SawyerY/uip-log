package com.example.uiplog.entity.log;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.example.uiplog.constant.CommConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;


@ApiModel
@Data
@Accessors(chain = true)
public class ScOptLog {

    public ScOptLog() {
    }

    public ScOptLog(String optApi, Integer optResultType, String requestMethod, String paramsUrl, String paramsBody, Integer ctUserId, String serverModule) {
        this.optApi = optApi;
        this.optResultType = optResultType;
        this.requestMethod = requestMethod;
        this.paramsUrl = paramsUrl;
        this.paramsBody = paramsBody;
        this.ctUserId = ctUserId;
        this.serverModule = serverModule;
    }

    private Long id;

    @ApiModelProperty(required = true, dataType = "string",  value = "接口方法名")
    private String optApi;
    @ApiModelProperty(required = true, dataType = "string",  value = "操作编号")
    private String optNo;
    @ApiModelProperty(required = true, dataType = "string",  value = "操作名称")
    @Excel(name = "日志类型")
    private String optName;

    private Integer optTypeId;

    private Integer optResultType;

    private String requestMethod;

    private String paramsUrl;

    private String paramsBody;
    @ApiModelProperty(required = true, dataType = "string",  value = "日志内容")
    @Excel(name = "日志内容")
    private String logDesc;
    @ApiModelProperty(required = true, dataType = "string",  value = "日志时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = CommConstant.TIMEZONE)
    @Excel(name = "操作时间")
    private Timestamp ctDate;

    private Integer ctUserId;

    private String ctRm;

    private String ctCal;
    @ApiModelProperty(required = true, dataType = "string",  value = "操作结果")
    @Excel(name = "操作结果")
    private String optResultTypeName;
    @ApiModelProperty(required = true, dataType = "string",  value = "操作人")
    @Excel(name = "操作员")
    private String ctUserName;

    @ApiModelProperty(dataType = "string",  value = "应用模块")
    private String serverModule;
}
