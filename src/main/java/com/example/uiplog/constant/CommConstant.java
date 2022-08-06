package com.example.uiplog.constant;

public class CommConstant {
    //系统时区
    public final static String TIMEZONE= "GMT+8";


    public final static String REDIS_CACHE = "COMM:CACHE"; //公用缓存
    public final static String REDIS_OPT = "COMM:OPT"; //Redis存储Key
    public final static String REDIS_ONLINE_USER = "ONLINE:USER";

    public final static Integer FAILED_FLAG = 601;
    public final static Integer SUCCESSED_FLAG = 600;
    public final static String SUCCESSED_MSG = "成功";
    public final static String FAILED_MSG = "失败";

    public final static String OTHER_KEY = "weds123";
    public final static String AES_SSO_USER_NO = "s;)*(+nmjdsf$#@c"; //登录工号加密key

    public final static String REDIS_WX_TOKEN = "QYWX:ACCESSTOKEN";

    public final static String CLIENT_FACE_APPH5="scm-face";

    public final static String REDIS_FACE_INFO = "FACE:INFO";
    public final static String IMG_HEAD = "fileAtt";

    public final static String  FACE_PATH = "face";  //人脸文件路径文件夹名


    public final static String MQ_CLIENT_MSG_TYPE = "msgType";
    public final static String MQ_CLIENT_DATA_INFO = "data";

    //后台服务api路由
    public final static String API_PATH_BASE= "/api-base";
    public final static String API_PATH_ATD= "/api-atd";
    public final static String API_PATH_ATD_APP= "/api-atd-app";
    public final static String API_PATH_DORM= "/api-dorm";
    public final static String API_PATH_DORM_APP= "/api-dorm-app";
    public final static String API_PATH_GATING_APP= "/api-gating-app";
    public final static String API_PATH_TRACK= "/api-track";
    public final static String API_PATH_ACCOUNT= "/api-account";
    public final static String API_PATH_OPENQR= "/v1";
    public final static String API_PATH_OPRNDEV= "";
    public final static String API_PATH_FACE= "/api-face";
    public final static String API_PATH_THIRD= "/api-third";
    public final static String API_PATH_SCENE= "/api-scene";

    // 记录原始记录的最大id
    public final static String RABBITMQ_SCREEN_MAX_ID = "RABBITMQ:GATINGRECORD:maxId";
    // 门禁记录上传（fanout）
    public final static String RABBITMQ_EXCHANGE_GATINGRECORD = "gatingRecordExchange";
    public final static String RABBITMQ_QUEUES_GATINGRECORD_UIP_DORM  = "gatingRecordQueueUipDorm";
    public final static String RABBITMQ_QUEUES_GATINGRECORD_UIP_ATD = "gatingRecordQueueUipAtd";
    /**************************设备中台消息队列******************************/
    public final static String RABBITMQ_EXCHANGE_OPEN_DEV_RECORD = "openDevRecordExchange"; //记录上传交换机
    public final static String RABBITMQ_QUEUE_OPEN_DEV_RECORD_UIP_BASE = "openDevRecordQueueUipBase"; //记录上传base服务消息队列
    //设备档案新增（topic）
    public final static String RABBITMQ_EXCHANGE_DEV_USER_ADD= "devUserAddExchange"; //设备档案下发交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_ADD_OPEN_DEV = "devUserAddQueueOpenDev"; //设备档案下发Opendev服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_ADD = "devUserAddRoutingKey"; //设备档案下发routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_ADD = "devUserAdd"; //设备档案下发消息标识
    public final static String RABBITMQ_EXCHANGE_DEV_USER_ADD_CONFIRM= "devUserAddConfirmExchange"; //设备档案下发回执交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_ADD_CONFIRM_UIP_BASE = "devUserAddConfirmQueueUipBase"; //设备档案下发回执base服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_ADD_CONFIRM = "devUserAddConfirmRoutingKey"; //设备档案下发回执routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_ADD_CONFIRM = "devUserAddConfirm"; //设备档案下发回执消息标识
    //设备档案修改（topic）
    public final static String RABBITMQ_EXCHANGE_DEV_USER_EDIT= "devUserEditExchange"; //设备档案修改交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_EDIT_OPEN_DEV = "devUserEditQueueOpenDev"; //设备档案修改Opendev服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_EDIT = "devUserEditRoutingKey"; //设备档案修改routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_EDIT = "devUserEdit"; //设备档案修改消息标识
    public final static String RABBITMQ_EXCHANGE_DEV_USER_EDIT_CONFIRM= "devUserEditConfirmExchange"; //设备档案修改回执交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_EDIT_CONFIRM_UIP_BASE = "devUserEditConfirmQueueUipBase"; //设备档案下发回执base服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_EDIT_CONFIRM = "devUserEditConfirmRoutingKey"; //设备档案修改回执routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_EDIT_CONFIRM = "devUserEditConfirm"; //设备档案修改回执消息标识
    //设备档案删除（topic）
    public final static String RABBITMQ_EXCHANGE_DEV_USER_REMOVE= "devUserRemoveExchange"; //设备档案删除交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_REMOVE_OPEN_DEV = "devUserRemoveQueueOpenDev"; //设备档案删除Opendev服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_REMOVE = "devUserRemoveRoutingKey"; //设备档案删除routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_REMOVE = "devUserRemove"; //设备档案删除消息标识
    public final static String RABBITMQ_EXCHANGE_DEV_USER_REMOVE_CONFIRM= "devUserRemoveConfirmExchange"; //设备档案删除回执交换机
    public final static String RABBITMQ_QUEUE_DEV_USER_REMOVE_CONFIRM_UIP_BASE = "devUserRemoveConfirmQueueUipBase"; //设备档案下发回执base服务消息队列
    public final static String RABBITMQ_ROUTINGKEY_DEV_USER_REMOVE_CONFIRM = "devUserRemoveConfirmRoutingKey"; //设备档案删除回执routingKey
    public final static String RABBITMQ_MSGTYPE_DEV_USER_REMOVE_CONFIRM = "devUserRemoveConfirm"; //设备档案删除回执消息标识
    //档案变更通知（fanout）
    public final static String RABBITMQ_EXCHANGE_CDC_USER= "cdcUserExchange"; //档案变更通知交换机
    public final static String RABBITMQ_QUEUE_CDC_USER_UIP_BASE= "cdcUserQueueUipBase"; //档案变更通知UipBase服务消息队列
    //人脸照片变更通知（fanout）
    public final static String RABBITMQ_EXCHANGE_CDC_FACE= "cdcFaceExchange"; //人脸照片变更通知交换机
    public final static String RABBITMQ_QUEUE_CDC_FACE_UIP_BASE= "cdcFaceQueueUipBase"; //人脸照片变更通知UipBase服务消息

    //人员授权变更通知（fanout）
    public final static String RABBITMQ_EXCHANGE_CDC_GRANT= "cdcGrantExchange"; //人-门授权变更通知交换机
    public final static String RABBITMQ_QUEUE_CDC_GRANT_UIP_BASE= "cdcGrantQueueUipBase"; //人-门授权变更通知UipBase服务消息队列
}
