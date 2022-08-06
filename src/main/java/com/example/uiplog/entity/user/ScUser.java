package com.example.uiplog.entity.user;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.weds.framework.core.common.model.JsonMapEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@ApiModel
@Data
public class ScUser {
    @ApiModelProperty(value = "用户id",required=true)
    private Integer userId;

    @ApiModelProperty(value = "用户编号",required=true)
    @Excel(name = "学号/工号")
    private String userNo;

    @ApiModelProperty(value = "用户名称",required=true)
    @Excel(name = "姓名")
    private String userName;

    @ApiModelProperty(value = "字典项：用户类型： 4.学生  5.老师  6.教职工",required=true)
    private Integer userType;
    @ApiModelProperty(value = "所属组织",required=true)
    private Integer deptId;
    @ApiModelProperty(value = "来自scm.dt_dep.dep_serial",required=true)
    private String deptNo;
    @Excel(name = "部门")
    @ApiModelProperty(value = "所属组织名称",required=true)
    private String deptName;

    @ApiModelProperty(value = "职务",required=true)
    private String userDuty;
    @ApiModelProperty(value = "职务",required=true)
    private String userDutyStr;
    @ApiModelProperty(value = "宿舍楼id",required=true)
    private Integer buildId;

    @ApiModelProperty(value = "宿舍楼名称",required=true)
    private String buildName;

    @ApiModelProperty(value = "手机",required=true)
    private String phoneNo;
    @ApiModelProperty(value = "MD5密码",required=true)
    private String userPassword;
    @ApiModelProperty(value = "身份证",required=true)
    private String userIdentity;
    @ApiModelProperty(value = "性别",required=true)
    private Integer userSex;
    @ApiModelProperty(value = "指纹",required=true)
    private String userFinger;
    @ApiModelProperty(value = "有无卡：0.无  1.有",required=true)
    private Integer userCard;
    @ApiModelProperty(value = "有无照片：0.无  1.有",required=true)
    private Integer userPhoto;
    @ApiModelProperty(value = "档案照片访问地址",required=true)
    private String photoSrc;
    @ApiModelProperty(value = "人脸识别：0.无  1.红外  2.可见光",required=true)
    private Integer userFace;
    @Excel(name = "人脸状态")
    @ApiModelProperty(value = "人脸状态名称",required=true)
    private String userFaceName;
    @ApiModelProperty(value = "个人资料：民族、学历、籍贯、生日、政治面貌、地址等",required=true)
    private JsonMapEntity personInfo;
    @ApiModelProperty(value = "学院id",required=true)
    private Integer instituteId;
    @Excel(name = "院系")
    @ApiModelProperty(value = "学院名称",required=true)
    private String instituteName;

    @ApiModelProperty(value = "年级id",required=true)
    private Integer gradeId;
    @ApiModelProperty(value = "年级名称",required=true)
    private String gradeName;
    @ApiModelProperty(value = "专业id",required=true)
    private Integer majorId;
    @ApiModelProperty(value = "专业名称",required=true)
    private String majorName;
    @ApiModelProperty(value = "邮箱",required=true)
    private String email;
    @ApiModelProperty(value = "第三方关联信息(微信、qq等)",required=true)
    private JsonMapEntity externalInfo;
    @ApiModelProperty(value = "入学（入职）日期",required=true)
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Timestamp userWorkday;
    @ApiModelProperty(value = "系统授权使用区间：开始时间",required=true)
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Timestamp limitBegin;
    @ApiModelProperty(value = "系统授权使用区间：截止时间",required=true)
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Timestamp limitEnd;
    @ApiModelProperty(value = "字典项：用户状态： 0.停用 1. 正常 2.删除",required=true)
    private Integer userState;
    @ApiModelProperty(value = "备注",required=true)
    private String remark;
    @ApiModelProperty(value = "排序",required=true)
    private Integer showOrder;
    @ApiModelProperty(value = "创建时间",required=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Timestamp ctDate;
    @ApiModelProperty(value = "创建人",required=true)
    private Integer ctUserId;
    @ApiModelProperty(value = "最后修改时间",required=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Timestamp ltDate;
    @ApiModelProperty(value = "最后修改人",required=true)
    private Integer ltUserId;
    @ApiModelProperty(value = "版本",required=true)
    private String version;
    @ApiModelProperty(value = "角色编号，拼接字符串",required=true)
    private String roleNos;
    @ApiModelProperty(value = "角色名称，拼接字符串",required=true)
    private String roleNames;
    @ApiModelProperty(value = "照片id",required=true)
    private Integer photoId;

    private String cardHao;

    private Integer ruleId;

    private String dormName;

    private String faceSrc;

    private String faceQualityStr;

    private Integer classId;
    @Excel(name = "班级")
    private String className;

    private String loginUserNo;

    @ApiModelProperty(value = "是否场所管理员",required=false)
    private Integer placeManagerState;

    private Integer cardType;

    private String openid;

    private Integer loginUserId;

    private Integer foreignState; //是否校外档案

    private Integer idType;

    private Integer faceQuality;//质量检测

    @Excel(name = "质量检测")
    private String faceQualityName;//质量检测名称

    private Integer faceQualityFrom;//检测来源

    @Excel(name = "检测来源")
    private String faceQualityFromName;//检测来源名称

    private Integer faceFrom;//照片来源
    @Excel(name = "照片来源")
    private Integer faceFromName;//照片来源名称

    public String getFaceQualityStr() {
        return faceQualityStr;
    }

    public void setFaceQualityStr(String faceQualityStr) {
        this.faceQualityStr = faceQualityStr;
    }

    public String getFaceSrc() {
        return faceSrc;
    }

    public void setFaceSrc(String faceSrc) {
        this.faceSrc = faceSrc;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getCardHao() {
        return cardHao;
    }

    public void setCardHao(String cardHao) {
        this.cardHao = cardHao;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getUserDutyStr() {
        return userDutyStr;
    }

    public void setUserDutyStr(String userDutyStr) {
        this.userDutyStr = userDutyStr;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty == null ? null : userDuty.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity == null ? null : userIdentity.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserFinger() {
        return userFinger;
    }

    public void setUserFinger(String userFinger) {
        this.userFinger = userFinger == null ? null : userFinger.trim();
    }

    public Integer getUserCard() {
        return userCard;
    }

    public void setUserCard(Integer userCard) {
        this.userCard = userCard;
    }

    public Integer getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Integer userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Integer getUserFace() {
        return userFace;
    }

    public void setUserFace(Integer userFace) {
        this.userFace = userFace;
    }

    public JsonMapEntity getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(JsonMapEntity personInfo) {
        this.personInfo = personInfo;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName == null ? null : instituteName.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public JsonMapEntity getExternalInfo() {
        return externalInfo;
    }

    public void setExternalInfo(JsonMapEntity externalInfo) {
        this.externalInfo = externalInfo;
    }

    public Timestamp getUserWorkday() {
        return userWorkday;
    }

    public void setUserWorkday(Timestamp userWorkday) {
        this.userWorkday = userWorkday;
    }


    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }



    public Integer getCtUserId() {
        return ctUserId;
    }

    public void setCtUserId(Integer ctUserId) {
        this.ctUserId = ctUserId;
    }

    public Timestamp getLimitBegin() {
        return limitBegin;
    }

    public void setLimitBegin(Timestamp limitBegin) {
        this.limitBegin = limitBegin;
    }

    public Timestamp getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Timestamp limitEnd) {
        this.limitEnd = limitEnd;
    }

    public Timestamp getCtDate() {
        return ctDate;
    }

    public void setCtDate(Timestamp ctDate) {
        this.ctDate = ctDate;
    }

    public Timestamp getLtDate() {
        return ltDate;
    }

    public void setLtDate(Timestamp ltDate) {
        this.ltDate = ltDate;
    }

    public Integer getLtUserId() {
        return ltUserId;
    }

    public void setLtUserId(Integer ltUserId) {
        this.ltUserId = ltUserId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getRoleNos() {
        return roleNos;
    }

    public void setRoleNos(String roleNos) {
        this.roleNos = roleNos;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
}
