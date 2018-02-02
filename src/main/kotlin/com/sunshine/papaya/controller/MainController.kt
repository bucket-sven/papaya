package com.sunshine.papaya.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest

@Controller
class MainController {
    val logger = LoggerFactory.getLogger(MainController::class.java)

    @RequestMapping("/", method = [ RequestMethod.GET ])
    fun home(map: ModelMap, request: HttpServletRequest): String {
//        var session = request.session
//        var key = session.getAttribute("key")
//        logger.info("key: {}", key)
//        if (key == null) {
//            session.setAttribute("key", "spring-boot")
//        }
        map.addAttribute("data", "This is Data!!!")
        return "index"
    }
}