package com.tungbt.app.controller

import com.tungbt.app.dto.response.HomeResponse
import com.tungbt.app.dto.response.HomeResponse.Companion.response
import com.tungbt.util.audit.Audit
import com.tungbt.util.common.IStringUtil
import com.tungbt.util.rest.request.Result
import com.tungbt.util.security.util.SecurityUtil
import com.tungbt.util.telegrambot.ITelegramBot
import com.tungbt.util.telegrambot.impl.TelegramBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    lateinit var stringUtil: IStringUtil

    @Autowired
    lateinit var securityUtil: SecurityUtil

    @Autowired
    lateinit var telegramBot: ITelegramBot

    @GetMapping("home")
    @Audit("HOME")
    fun home(): HomeResponse {
        var test: String? = "test";
        test = stringUtil.uppercase(test)
        return response {
            data = test
            result = Result.OK
        }
    }

    @GetMapping("test")
    @Audit("TEST")
    fun test(): HomeResponse {
        val test: String? = telegramBot.getMe()
        return response {
            data = test
            result = Result.OK
        }
    }

    @GetMapping("info")
    @Audit("INFO")
    @PreAuthorize("hasAuthority('SCOPE_articles.read.test')")
    fun info(): ResponseEntity<Map<String, Any>> {
        val data: Map<String, Any> = securityUtil.jwtGetInfo()
        return ResponseEntity.ok(data)
    }

}