package com.tungbt.util.common.impl

import com.google.gson.Gson
import com.tungbt.util.audit.aspect.AuditAspect
import com.tungbt.util.common.IRestUtil
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.util.concurrent.TimeUnit

import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RestUtil: IRestUtil {

    private val logger: Logger = LoggerFactory.getLogger(RestUtil::class.java)
    private val mediaType: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    private val client: OkHttpClient = OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build()

    override fun callGet(url: String, headers: Map<String, String>, params: Map<String, String>): String {
        TODO("Not yet implemented")
    }

    override fun callPost(url: String, headers: Map<String, String>, body: Map<String, String>): String {
        logger.info("HttpOk url: {} body: {}", url, body)
        val bodyStr: String = Gson().toJson(body)
        val request = Request.Builder()
            .url(url)
            .headers(headers.toHeaders())
            .post(bodyStr.toRequestBody(mediaType))
            .build()
        val response: String = client.newCall(request).execute().body.toString()
        logger.info("HttpOk response: {}", response)
        return response
    }


}