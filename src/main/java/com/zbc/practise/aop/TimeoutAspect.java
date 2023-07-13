package com.zbc.practise.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Aspect
@Component
public class TimeoutAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimeoutAspect.class);
    private static ExecutorService executorService;

    public static synchronized ExecutorService getThreadPool() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }
        return executorService;
    }

    @Around("@annotation(timeoutCheck)")
    public Object checkTimeout(ProceedingJoinPoint joinPoint, OperationTimeout timeoutCheck) throws Throwable {
        // 从注解中获取超时时间
        long timeoutMillis = timeoutCheck.timeout();
        logger.info("接口被监控进入");
        Future<Object> future = executorService.submit(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        try {
            return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // 断开与客户端的连接
            logger.info("接口响应超时，断开与客户端的连接");
            // 自定义的异常
            // throw new TimeoutDisconnectException("接口响应超时，断开与客户端的连接");
        } finally {
            executorService.shutdown();
            // 取消任务
            logger.info("终止接口的执行");
            future.cancel(true);
        }
        return null;
    }

}
