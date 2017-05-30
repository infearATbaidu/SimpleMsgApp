package com.app.msg.common.log;


import com.app.msg.common.utils.JsonUtils;
import com.app.msg.interfaces.BaseResponse;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 功能描述：Logger AOP日志输出，统计调用执行时间和日志
 */
@Aspect
@Component
public class LoggerInterceptor implements MethodInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    /**
     * Aspect输出日志
     *
     * @param mi
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.app.msg.common.log.LoggerOut)")
    public Object loggerOut(ProceedingJoinPoint mi) throws Throwable {
        ProxyMethodInvocation method =
                (ProxyMethodInvocation) FieldUtils.readDeclaredField(mi,
                        "methodInvocation", true);
        // 调用AOP方法拦截输出
        return this.invoke(method);
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        // 整理调用方法的信息;
        String methodSimpleInfo = getMethodSimpleName(mi);
        long start = System.currentTimeMillis();

        // 判断Annotation是否需要记录日志
        LoggerOut loggerOut = mi.getMethod().getAnnotation(LoggerOut.class);

        try {
            Object result = mi.proceed();

            if ((loggerOut != null && !loggerOut.enable()) || (!logger.isDebugEnabled() && loggerOut.debug())) {
                return result;
            }

            if (loggerOut.enable()) {
                String status = "OK";

                if (result instanceof BaseResponse) {
                    if (!((BaseResponse) result).isSuccess()) {
                        status = "FAIL";
                    }
                }

                try {
                    if (!loggerOut.onlyReq()) {
                        if (loggerOut.duration()) {
                            if (loggerOut.format() == LoggerFormat.JSON) {
                                logger.info("Call={} Status={} Spend={} Request={} Response=[{}]", methodSimpleInfo,
                                        status, (System.currentTimeMillis() - start), mi.getArguments(),
                                        JsonUtils.toJson(result));
                            } else {
                                logger.info("Call={} Status={} Spend={} Request={} Response=[{}]", methodSimpleInfo,
                                        status, (System.currentTimeMillis() - start), mi.getArguments(), result);
                            }
                        } else {
                            if (loggerOut.format() == LoggerFormat.JSON) {
                                logger.info("Call={} Status={} Request={} Response=[{}]", methodSimpleInfo, status,
                                        mi.getArguments(), JsonUtils.toJson(result));
                            } else {
                                logger.info("Call={} Status={} Request={} Response=[{}]", methodSimpleInfo, status,
                                        mi.getArguments(), result);
                            }
                        }
                    } else {
                        if (loggerOut.duration()) {
                            if (loggerOut.format() == LoggerFormat.JSON) {
                                logger.info("Call={} Status={} Spend={} Request={}", methodSimpleInfo, status,
                                        (System.currentTimeMillis() - start), mi.getArguments());
                            } else {
                                logger.info("Call={} Status={} Spend={} Request={}", methodSimpleInfo, status,
                                        (System.currentTimeMillis() - start), mi.getArguments());
                            }
                        } else {
                            if (loggerOut.format() == LoggerFormat.JSON) {
                                logger.info("Call={} Status={} Request={}", methodSimpleInfo, status,
                                        mi.getArguments());
                            } else {
                                logger.info("Call={} Status={} Request={}", methodSimpleInfo, status,
                                        mi.getArguments());
                            }
                        }
                    }
                } catch (Throwable t) {
                    logger.debug("printReq fail.");
                }
            }

            return result;
        } catch (Throwable t) {
            if (loggerOut.duration()) {
                logger.error("Call={} Status=ERR Spend={} Request={}", methodSimpleInfo,
                        (System.currentTimeMillis() - start), mi.getArguments(), t);
            } else {
                logger.error("Call={} Status=ERR Request={}", methodSimpleInfo, mi.getArguments(), t);
            }
            throw t;
        }
    }

    /**
     * 取得方法名称
     *
     * @param mi
     * @return
     */
    private static String getMethodSimpleName(MethodInvocation mi) {
        return new StringBuilder(ClassUtils.getSimpleName(mi.getThis().getClass())).append(".")
                .append(mi.getMethod().getName()).toString();
    }
}
