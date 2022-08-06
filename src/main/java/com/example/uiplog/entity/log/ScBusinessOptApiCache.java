package com.example.uiplog.entity.log;

public class ScBusinessOptApiCache {

    private String optApi;//接口api

    private String optNo;//操作编号

    private String optName;//操作名称

    private Integer optTypeId;//操作类型

    private String logDescSql;//操作日志模版


    public String getOptNo() {
        return optNo;
    }

    public void setOptNo(String optNo) {
        this.optNo = optNo;
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
        this.logDescSql = logDescSql;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getOptApi() {
        return optApi;
    }

    public void setOptApi(String optApi) {
        this.optApi = optApi;
    }

}
