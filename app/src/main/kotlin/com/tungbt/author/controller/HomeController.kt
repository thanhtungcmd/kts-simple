package com.tungbt.author.controller

import com.tungbt.util.audit.Audit
import com.tungbt.util.common.IStringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    lateinit var stringUtil: IStringUtil

    @GetMapping("home")
    @Audit("HOME")
    fun home(): ResponseEntity<String> {
        var test: String? = "test";
        test = stringUtil.uppercase(test);
        return ResponseEntity.ok(test)
    }

}