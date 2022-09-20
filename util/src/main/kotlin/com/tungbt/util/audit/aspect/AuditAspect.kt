package com.tungbt.util.audit.aspect

import com.tungbt.util.audit.Audit
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class AuditAspect {

    private val logger: Logger = LoggerFactory.getLogger(AuditAspect::class.java)

    @Around("@annotation(com.tungbt.util.audit.Audit)")
    fun aroundAdvice(joinPoint: ProceedingJoinPoint) : Object {
        var startTime: Long = System.currentTimeMillis()
        // get param
        var signature: MethodSignature = joinPoint.signature as MethodSignature
        var method: Method = signature.method
        var audit: Audit = method.getAnnotation(Audit::class.java)
        var data: Object = joinPoint.proceed() as Object
        // take time
        var takeTime: Long = System.currentTimeMillis() - startTime
        logger.info("Time Taken by {} is {} milli second", audit.useCase, takeTime)
        return data
    }

}