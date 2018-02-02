package com.sunshine.papaya.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.sunshine.papaya.util.ConstantUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap

@Controller
class ErrorHandlerController(dea: DefaultErrorAttributes) : AbstractErrorController(dea) {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun getErrorPath(): String {
        return ConstantUtil.ERROR_PATH
    }

    @RequestMapping(ConstantUtil.ERROR_PATH)
    fun error(request: HttpServletRequest, response: HttpServletResponse, map: ModelMap): String {
        var model = Collections.unmodifiableMap(getErrorAttributes(request, false))
        var cause = getCause(request)
        var status = model["status"] as Int
        var message = model["message"] as String
        var errorMessage = "Internal Server Error"
        response.status = status
        var view = "5xx"
        if (!isJsonRequest(request)) {
            map.addAttribute("timestamp", Date())
            map.addAttribute("message", errorMessage)
            map.addAttribute("status", status)
            map.addAttribute("error", cause)
        } else {
            var error = HashMap<String, Any>()
            error["success"] = false
            error["errorMessage"] = errorMessage
            error["message"] = message
            writeJson(response, error)
        }
        return view
    }

    fun getCause(request: HttpServletRequest): Throwable {
        var error = request.getAttribute("javax.servlet.error.exception") as Throwable?
        if (error != null) {
            while (error is ServletException && error.cause != null) {
                error = error.cause
            }
        }
        return error!!
    }

    fun isJsonRequest(request: HttpServletRequest): Boolean {
        var requestURI = request.getAttribute("javax.servlet.error.request_uri") as String?
        if (requestURI != null && requestURI.endsWith(".json")) {
            return true
        } else {
            return request.getHeader(HttpHeaders.ACCEPT).contains(MediaType.APPLICATION_JSON_VALUE)
        }
    }

    fun writeJson(response: HttpServletResponse, data: HashMap<String, Any>) {
        var writer = response.writer
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        objectMapper.writeValue(writer, data)
    }
}