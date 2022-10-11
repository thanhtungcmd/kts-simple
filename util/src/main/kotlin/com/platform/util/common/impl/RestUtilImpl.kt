package com.tungbt.util.common.impl

import com.google.gson.Gson
import com.tungbt.util.common.RestUtil
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

@Component
class RestUtilImpl: RestUtil {

    private val logger: Logger = LoggerFactory.getLogger(RestUtil::class.java)
    private val mediaType: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    private val client: OkHttpClient = OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build()

    override fun callGet(url: String, headers: Map<String, String>, params: Map<String, String>): String? {
        logger.info("HttpOk url: {} params: {}", url, params)
        val request = Request.Builder()
            .url("$url?"+paramConvert(params))
            .headers(headers.toHeaders())
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    override fun callGet(url: String, headers: Map<String, String>): String? {
        logger.info("HttpOk url: {}", url)
        val request = Request.Builder()
            .headers(headers.toHeaders())
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    override fun callGet(url: String): String? {
        logger.info("HttpOk url: {}", url)
        val request = Request.Builder()
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    override fun callPost(url: String, headers: Map<String, String>, body: Map<String, String>): String? {
        logger.info("HttpOk url: {} body: {}", url, body)
        val bodyStr: String = Gson().toJson(body)
        val request = Request.Builder()
            .url(url)
            .headers(headers.toHeaders())
            .post(bodyStr.toRequestBody(mediaType))
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    override fun callPost(url: String, body: Map<String, String>): String? {
        logger.info("HttpOk url: {} body: {}", url, body)
        val bodyStr: String = Gson().toJson(body)
        val request = Request.Builder()
            .url(url)
            .post(bodyStr.toRequestBody(mediaType))
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    override fun callPost(url: String): String? {
        logger.info("HttpOk Post url: {}", url)
        val request = Request.Builder()
            .url(url)
            .build()
        val response: String? = client.newCall(request).execute().body?.string()
        logger.info("HttpOk response: {}", response)
        return response
    }

    private fun paramConvert(map: Map<String, String>): String {
        val sb = StringBuilder()
        for ((key, value) in map) {
            if (sb.isNotEmpty()) {
                sb.append("&")
            }
            sb.append(
                String.format(
                    "%s=%s",
                    urlEncodeUtf8(key),
                    urlEncodeUtf8(value)
                )
            )
        }
        return sb.toString()
    }

    private fun urlEncodeUtf8(s: String?): String? {
        return try {
            URLEncoder.encode(s, "UTF-8")
        } catch (e: Exception) {
            throw UnsupportedOperationException(e)
        }
    }

}