package br.com.quintoandar.springbootapitemplate.api.interceptors;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class PerformanceMonitorConfiguration {

    @Bean
    public Advisor performanceMonitorAdvisor() {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        final String pointcutExpression = "within(@org.springframework.web.bind.annotation.RestController *) "
                + "|| within(@org.springframework.stereotype.Repository *)";
        pointcut.setExpression(pointcutExpression);
        return new DefaultPointcutAdvisor(pointcut, new PerformanceMonitorInterceptorToInfo(true));
    }

}
