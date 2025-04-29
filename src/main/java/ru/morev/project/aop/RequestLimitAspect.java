package ru.morev.project.aop;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Aspect
@Component
public class RequestLimitAspect {

    @Value("${request.limit.max:5}")
    private int maxRequests;

    private final ConcurrentHashMap<String, AtomicInteger> requestCounters = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        log.info("🔧 AOP активен: лимит {} запросов на метод", maxRequests);
    }

    @Around("execution(* ru.morev.project.controller..*(..))")
    public Object limitRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        requestCounters.putIfAbsent(methodName, new AtomicInteger(0));
        int currentCount = requestCounters.get(methodName).incrementAndGet();

        if (currentCount > maxRequests) {
            log.warn("⚠️ Метод {} превысил лимит запросов ({}).", methodName, maxRequests);
            throw new RequestLimitExceededException("Превышен лимит вызовов метода: " + methodName);
        }

        return joinPoint.proceed();
    }

    public static class RequestLimitExceededException extends RuntimeException {
        public RequestLimitExceededException(String message) {
            super(message);
        }
    }
}
