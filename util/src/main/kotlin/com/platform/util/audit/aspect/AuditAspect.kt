package com.platform.util.audit.aspect

import com.platform.util.audit.Audit
import com.platform.util.common.ReflectUtil
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class AuditAspect {

    @Autowired
    lateinit var reflectUtil: ReflectUtil

    private val logger: Logger = LoggerFactory.getLogger(AuditAspect::class.java)

    @Around("@annotation(com.platform.util.audit.Audit)")
    fun aroundAdvice(joinPoint: ProceedingJoinPoint) : Any {
        val audit: Audit = getAudit(joinPoint)
        logger.info("{} =========== Start {} ===========", reflectUtil.getStack(), audit.name)
        try {
            val proceed: Any = joinPoint.proceed() as Any
            return proceed
        } catch (ex: Throwable) {
            logger.error("{} =========== An error occur when execute {} ===========", reflectUtil.getStack(), audit.name)
            throw ex
        } finally {
            logger.info("{} =========== End {} ===========", reflectUtil.getStack(), audit.name)
        }
    }

    @Around("@annotation(com.platform.util.audit.ExecuteTime)")
    fun executeTime(joinPoint: ProceedingJoinPoint) : Any {
        val startTime: Long = System.currentTimeMillis()
        val proceed: Any = joinPoint.proceed() as Any
        val takeTime: Long = System.currentTimeMillis() - startTime
        logger.info("{} Method={}, executed in duration (ms)={}", reflectUtil.getStack(), joinPoint.signature, takeTime)
        return proceed
    }

    private fun getAudit(joinPoint: ProceedingJoinPoint): Audit {
        val signature: MethodSignature = joinPoint.signature as MethodSignature
        val method: Method = signature.method
        return method.getAnnotation(Audit::class.java)
    }

}