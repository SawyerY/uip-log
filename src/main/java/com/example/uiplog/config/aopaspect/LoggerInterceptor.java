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
                }

                if (response.getCode() != 600) {
                    commOptLogService.sysLogInsert(
                            new ScOptLog(apiName,
                                    2,this.request.getMethod(),
                                    this.request.getQueryString(),JSON.toJSONString(params),
                                    userId,
                                    client.split("_")[1]
                            ));
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
            }
        }

    }
}
