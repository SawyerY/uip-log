package com.example.uiplog.config.aopaspect;

import com.alibaba.fastjson2.JSON;
import com.example.uiplog.annotation.OptLog;
import com.example.uiplog.entity.log.ScOptLog;
import com.example.uiplog.service.comm.CommOptLogService;
import com.google.gson.Gson;
import com.weds.framework.core.common.model.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LoggerInterceptor {
    private Logger log = LogManager.getLogger();
    @Autowired
    private HttpServletRequest request;
/*    @Autowired
    private HttpServletResponse response;*/
    @Autowired
    private CommOptLogService commOptLogService;

    public LoggerInterceptor() {
    }

    @Pointcut("@annotation(com.example.uiplog.annotation.OptLog)")
    public void userPointCut() {
    }

    @Pointcut("@annotation(com.example.uiplog.annotation.OptLog)")
    public void sysPointCut() {
    }

    private Method findMethodByName(JoinPoint point) {
        String methodName = point.getSignature().getName();
        if (null != methodName && !"".equals(methodName)) {
            Method[] methods = point.getSignature().getDeclaringType().getMethods();
            Method[] var4 = methods;
            int var5 = methods.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Method method = var4[var6];
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }
        }

        return null;
    }

    @Around("userPointCut()")
    public Object userLogWrite(ProceedingJoinPoint point) throws Throwable {
        Object obj = null;
        Gson gson = new Gson();
        Method method = this.findMethodByName(point);
        Class<? extends Object> clazz = point.getTarget().getClass();
        //接口权限检查
        String apiName = method.getName()+"@"+clazz.getName();
        Integer userId = Integer.valueOf(this.request.getAttribute("loginUserId").toString());
        String client = this.request.getAttribute("loginClient").toString();
        // TODO: 2020/5/8 调试阶段 注释
/*        if(!commOptControlService.sysApiCheck(apiName,userId,client)){
            this.response.setStatus(403);
            return obj;
        }*/

        try {
            obj = point.proceed();
        } catch (Throwable var26) {
            throw var26;
        }

        if (method != null) {
            boolean hasAnnotation = method.isAnnotationPresent(OptLog.class);
            if (hasAnnotation) {
                OptLog logInfo = (OptLog)method.getAnnotation(OptLog.class);
                boolean valid = logInfo.valid();
                if (!valid) {
                    return obj;
                }

                String methodDesc = logInfo.value();
                if (method.isAnnotationPresent(ApiOperation.class) && "".equals(methodDesc)) {
                    methodDesc = ((ApiOperation)method.getAnnotation(ApiOperation.class)).value();
                }

                String logPosition = logInfo.logsPostion().toString();
                Object[] params = point.getArgs();
                String strArgs = "";

                for(int j = 0; j < params.length; ++j) {
                    String name = null;
                    if (params[j] != null) {
                        name = params[j].getClass().getName();
                    }

                    if (j == params.length - 1) {
                        strArgs = strArgs + name;
                    } else {
                        strArgs = strArgs + name + "|";
                    }
                }

                String argsvalue = "";
                if (OptLog.Position.Action.toString().equals(logPosition)) {
                    Enumeration<?> paraNames = this.request.getParameterNames();
                    Map<String, Object> map = new HashMap();
                    Enumeration e = paraNames;

                    while(e.hasMoreElements()) {
                        String thisName = e.nextElement().toString();
                        String thisValue = this.request.getParameter(thisName);
                        map.put(thisName, thisValue);
                    }

                    if (map.keySet().size() > 0) {
                        argsvalue = gson.toJson(map);
                    } else {
                        Object[] args = point.getArgs();
                        if (args != null) {
                            argsvalue = gson.toJson(args);
                        }
                    }
                }
                JsonResult response = (JsonResult) obj;
                if (response.getCode() == 600) {
                    commOptLogService.sysLogInsert(
                            new ScOptLog(apiName,
                                    1,this.request.getMethod(),
                                    this.request.getQueryString(),JSON.toJSONString(params),
                                    userId,
                                    client.split("_")[1]
                            ));
                    /*this.log.info("类名称:" + clazz.getName());
                    this.log.info("日志位置:" + logPosition);
                    this.log.info("方法名称:" + method.getName());
                    this.log.info("方法描述:" + methodDesc);
                    this.log.info("方法参数:" + strArgs);
                    this.log.info("参数内容:" + argsvalue.replaceAll("'", "''"));
                    this.log.info("方法耗时:" + String.valueOf(offTime));
                    this.log.info("日志日期:" + sdf.format(new Date()));
                    this.log.info("---------------------------------------------------");*/
                }

                if (response.getCode() != 600) {
                    commOptLogService.sysLogInsert(
                            new ScOptLog(apiName,
                                    2,this.request.getMethod(),
                                    this.request.getQueryString(),JSON.toJSONString(params),
                                    userId,
                                    client.split("_")[1]
                            ));
                    /*this.log.error("类名称:" + clazz.getName());
                    this.log.error("日志位置:" + logPosition);
                    this.log.error("方法名称:" + method.getName());
                    this.log.error("方法描述:" + methodDesc);
                    this.log.error("方法参数:" + strArgs);
                    this.log.error("参数内容:" + argsvalue.replaceAll("'", "''"));
                    this.log.error("方法耗时:" + String.valueOf(offTime));
                    this.log.error("日志日期:" + sdf.format(new Date()));
                    this.log.error("错误信息:" + errMsg);
                    this.log.error("---------------------------------------------------");*/
                }
            }
        }

        return obj;
    }

    @AfterThrowing(
            value = "sysPointCut()",
            throwing = "e"
    )
    public void throwLogWrite(JoinPoint point, Exception e) {
        Method method = this.findMethodByName(point);
        if (method != null) {
            Class<? extends Object> clazz = point.getTarget().getClass();

            if (this.log.isErrorEnabled()) {
                commOptLogService.sysLogInsert(
                        new ScOptLog(method.getName()+"@"+clazz.getName(),
                                2,this.request.getMethod(),
                                this.request.getQueryString(),"",
                                Integer.valueOf(this.request.getAttribute("loginUserId").toString()),
                                this.request.getAttribute("loginClient").toString().split("_")[1]
                        ));
                /*this.log.error(logPosition);
                this.log.error("类名称:" + clazz.getName());
                this.log.error("方法名称:" + method.getName());
                this.log.error("方法描述:" + methodDesc);
                this.log.error("方法参数:" + strArgs);
                this.log.error("参数内容:" + argsvalue);
                this.log.error("日志日期:" + sdf.format(new Date()));
                this.log.error("错误信息:" + e.getLocalizedMessage());
                this.log.error("错误信息:" + e.toString());
                this.log.error("---------------------------------------------------");*/
            }
        }

    }
}
