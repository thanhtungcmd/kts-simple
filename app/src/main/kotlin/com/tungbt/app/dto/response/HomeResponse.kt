package com.tungbt.app.dto.response

import com.tungbt.util.rest.request.BaseResponse
import com.tungbt.util.rest.request.Result


class HomeResponse(
    override var data: Any?,
    override var result: Result
) : BaseResponse() {

    private constructor(builder: Builder) : this(builder.data, builder.result)

    companion object {
        inline fun response(block: HomeResponse.Builder.() -> Unit) = HomeResponse.Builder().apply(block).build()
    }

    class Builder {
        var data: Any? = null
        var result: Result = Result.OK
        fun build() = HomeResponse(this)
    }
}