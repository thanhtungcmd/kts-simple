package com.platform.app.dto.response

import com.tungbt.util.rest.response.BaseResponseAbstract
import com.tungbt.util.rest.response.Result

class HomeResponse(
    override var data: Any?,
    override var result: Result
) : BaseResponseAbstract() {

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