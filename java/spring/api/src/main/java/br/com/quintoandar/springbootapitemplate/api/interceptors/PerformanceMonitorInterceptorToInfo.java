package br.com.quintoandar.springbootapitemplate.api.interceptors;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;
import org.springframework.util.StopWatch;

public class PerformanceMonitorInterceptorToInfo extends AbstractMonitoringInterceptor {

    public PerformanceMonitorInterceptorToInfo(final boolean useDynamicLogger) {
        super();
        setUseDynamicLogger(useDynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(final MethodInvocation invocation, final Log log) throws Throwable {
        final String name = createInvocationTraceName(invocation);
        final StopWatch stopWatch = new StopWatch(name);
        stopWatch.start(name);
        try {
            return invocation.proceed();
        } finally {
            stopWatch.stop();
            log.info(stopWatch.shortSummary());
        }
    }
}
