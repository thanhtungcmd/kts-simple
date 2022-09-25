package com.tungbt.util.rest.request

import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
interface Result {
    var result: Boolean
    var message: String?
    var code: String?

    companion object OK : Result {
        override var result = true
        override var message: String? = ResponseCode.SUCCESS.message
        override var code: String? = ResponseCode.SUCCESS.code
    }
}