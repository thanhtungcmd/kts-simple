package com.tungbt.util.common.impl

import com.tungbt.util.common.IStringUtil
import org.springframework.stereotype.Component

@Component
class StringUtil: IStringUtil {

    override fun isNull(s:String?): Boolean {
        return s.isNullOrBlank();
    }

    override fun nvl(s: String?): String {
        if (s.isNullOrBlank()) {
            return ""
        }
        return s.trim()
    }

    override fun uppercase(s: String?): String {
        return nvl(s).uppercase();
    }


}