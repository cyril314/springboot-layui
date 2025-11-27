package com.fit.aop;

import com.fit.entity.SysOperationLog;
import com.fit.service.SysOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectionHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Spring AOP 实现的业务日志切面
 */
@Slf4j
@Component
public class BizLogAspect implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    @Autowired
    private SysOperationLogService operationLogService;
    // 使用线程池进行异步处理
    private final ExecutorService asyncExecutor = Executors.newFixedThreadPool(5);
    // ThreadLocal 用于在方法调用间传递日志对象
    private final ThreadLocal<SysOperationLog> logThreadLocal = new ThreadLocal<>();

    /**
     * 获取参数值的描述
     */
    private String getValueDescription(Object value) {
        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        } else if (value instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value);
        } else if (value instanceof Collection) {
            return "Collection(size=" + ((Collection<?>) value).size() + ")";
        } else if (value instanceof Map) {
            return "Map(size=" + ((Map<?, ?>) value).size() + ")";
        } else if (value.getClass().isArray()) {
            return "Array(length=" + java.lang.reflect.Array.getLength(value) + ")";
        } else {
            return value.toString();
        }
    }

    /**
     * 获取方法参数描述 - 格式：参数名: 参数值
     */
    private String getMethodParamsDescription(Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return "无参数";
        }
        StringBuilder sb = new StringBuilder();
        java.lang.reflect.Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            // 获取参数名
            String paramName = i < parameters.length ? parameters[i].getName() : "arg" + i;
            // 格式：参数名: 参数值
            sb.append(paramName).append(": ").append(getValueDescription(args[i]));
        }
        return sb.toString();
    }

    /**
     * 设置操作人信息
     */
    private void setOperatorInfo(SysOperationLog operationLog) {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null && subject.getPrincipal() != null) {
                Long userId = (Long) subject.getPrincipal();
                operationLog.setUserId(userId);
            }
        } catch (Exception e) {
            log.error("设置操作人信息失败", e);
        }
    }

    /**
     * 方法执行前通知
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 检查方法是否有 @BizLog 注解
        BizLog bizLog = method.getAnnotation(BizLog.class);
        if (bizLog == null) {
            return;
        }
        SysOperationLog operationLog = new SysOperationLog();
        // 设置基本信息
        operationLog.setLogType(bizLog.logType());
        operationLog.setLogName(bizLog.logName());
        operationLog.setClassName(target.getClass().getName());
        operationLog.setMethod(method.getName());
        operationLog.setCtime(new Date());
        if (bizLog.recordParams()) {
            String paramsDescription = getMethodParamsDescription(method, args);
            if (!paramsDescription.isEmpty()) {
                operationLog.setMessage("参数-> " + paramsDescription);
            }
        }
        // 设置操作人信息
        setOperatorInfo(operationLog);
        // 保存到ThreadLocal
        logThreadLocal.set(operationLog);
    }

    /**
     * 方法成功返回后通知
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        SysOperationLog operationLog = logThreadLocal.get();
        if (operationLog == null) {
            return;
        }
        try {
            operationLog.setSucceed("1");
//            saveLogAsync(operationLog);
        } finally {
            logThreadLocal.remove();
        }
    }

    /**
     * 方法抛出异常后通知
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        SysOperationLog operationLog = logThreadLocal.get();
        if (operationLog == null) {
            return;
        }
        try {
            operationLog.setSucceed("0"); // 失败
            // 记录异常信息
            operationLog.setMessage(operationLog.getMessage() + " | 异常信息-> " + ex.getMessage());
            // 异步保存日志
            saveLogAsync(operationLog);
        } finally {
            logThreadLocal.remove();
        }
    }

    /**
     * 异步保存日志
     */
    private void saveLogAsync(SysOperationLog operationLog) {
        asyncExecutor.execute(() -> {
            try {
                operationLogService.save(operationLog);
                log.debug("操作日志保存成功: {}", operationLog.getLogName());
            } catch (Exception e) {
                log.error("保存操作日志失败: {}", e.getMessage());
            }
        });
    }
}