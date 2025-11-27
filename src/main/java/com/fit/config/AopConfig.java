package com.fit.config;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public DefaultPointcutAdvisor bizLogAdvisor(com.fit.aop.BizLogAspect bizLogAspect) {
        // 创建切入点：匹配所有带有 @BizLog 注解的方法
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null, com.fit.aop.BizLog.class);
        // 创建通知器
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(bizLogAspect);
        advisor.setOrder(1); // 设置执行顺序

        return advisor;
    }
}