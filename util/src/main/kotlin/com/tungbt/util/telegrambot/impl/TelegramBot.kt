package com.tungbt.util.telegrambot.impl

import com.tungbt.util.common.IRestUtil
import com.tungbt.util.telegrambot.ITelegramBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TelegramBot: ITelegramBot {

    private val url: String = "https://api.telegram.org/bot"

    @Value("\${tungbt.telegrambot.token}")
    lateinit var token: String

    @Autowired
    lateinit var restUtil: IRestUtil

    override fun getMe(): String? {
        val response: String? = restUtil.callPost("$url$token/getMe")
        return response
    }

}