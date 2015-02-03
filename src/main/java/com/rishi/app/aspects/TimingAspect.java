package com.rishi.app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rishi.app.annotations.Timed;

@Aspect
public class TimingAspect {
    private static final Logger log = LoggerFactory.getLogger(TimingAspect.class);

    @Around("execution(* *(..)) && @annotation(Timed)")
    public Object logPerformanceForTimedMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String tag = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        long start = System.nanoTime();
        Object o = joinPoint.proceed();
        double elapsedTime = (double) (System.nanoTime() - start)/ 1000000000.0;
        log.info("tag[" + tag + "] time[" + String.valueOf(elapsedTime) + "s]");
        return o;
    }
}
