package com.platform.app.controller

import com.platform.app.dto.response.HomeResponse
import com.platform.app.dto.response.HomeResponse.Companion.response
import com.platform.util.audit.Audit
import com.platform.util.audit.ExecuteTime
import com.platform.util.common.CipherUtil
import com.platform.util.common.StringUtil
import com.platform.util.security.util.SecurityUtil
import com.platform.util.telegrambot.TelegramBot
import com.platform.util.rest.response.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    lateinit var stringUtil: StringUtil

    @Autowired
    lateinit var securityUtil: SecurityUtil

    @Autowired
    lateinit var telegramBot: TelegramBot

    @Autowired
    @Qualifier("AES128Util")
    lateinit var cipherUtil: CipherUtil

    @GetMapping("home")
    @Audit("HOME")
    @ExecuteTime
    fun home(): HomeResponse {
        var test: String? = "test";
        test = stringUtil.upper(test)
        return response {
            data = test
            result = Result.OK
        }
    }

    @GetMapping("test")
    @Audit("TEST")
    @ExecuteTime
    fun test(): HomeResponse {
        val test: String? = telegramBot.getUpdates()
        //val encode: String = stringUtil.nvl(cipherUtil.encrypt("123", "6w9zzz&F)J@NcQfT"))
        //val decode: String = stringUtil.nvl(cipherUtil.decrypt(encode, "6w9zzz&F)J@NcQfT"))
        //val list = mutableListOf(encode, decode)
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