package com.sunshine.papaya.filter

import com.sunshine.papaya.util.ConstantUtil
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LogFilter {
    private val logger = LoggerFactory.getLogger(LogFilter::class.java)

    @Around(ConstantUtil.CONTROLLER_ASPECT)
    fun printUrlLog(joinPoint: ProceedingJoinPoint): Any? {
        var obj: Any? = null
        var args: Array<Any> = joinPoint.args
        var startTime = System.currentTimeMillis()
        var status = HttpStatus.OK.value()

        try {
            obj = joinPoint.proceed(args)
            return obj
        } catch (e: Throwable) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            throw e
        } finally {
            printLog(joinPoint, startTime, status)
        }
        return null
    }

    private fun printLog(joinPoint: ProceedingJoinPoint, startTime: Long, status: Int) {
        var endTime = System.currentTimeMillis()
        var signature = joinPoint.signature as MethodSignature
        var methodName = signature.declaringTypeName + "." + signature.name
        var requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        var request = requestAttributes.request
        var path = request.servletPath
        printExecTime(request.method, path, methodName, startTime, endTime, status)
    }

    fun printExecTime(httpMethod: String, path: String, methodName: String, startTime: Long, endTime: Long, status: Int) {
        var diffTime = endTime - startTime
        logger.info("{} {} {}, {} {} ms", httpMethod, path, methodName, status, diffTime)
    }

//    fun getResponseStatus(): Int {
//        var attrs = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
//        var webRequest = ServletWebRequest(attrs.request)
//        var ea =this.errorAttributes.getErrorAttributes(webRequest, false)
//        var model = Collections.unmodifiableMap(ea)
//        return model["status"] as Int
//    }
}