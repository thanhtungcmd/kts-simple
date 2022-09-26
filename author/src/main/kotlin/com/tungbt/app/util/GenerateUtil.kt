package com.tungbt.app.util

import com.tungbt.app.entity.User
import com.tungbt.app.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import java.util.ArrayList

@Service
class GenerateUtil {

    @Autowired
    lateinit var userRepository: UserRepository

    fun generateDummyUsers() {
        val passwordEncoder = BCryptPasswordEncoder()
        var userOne = User(null, "admin", passwordEncoder.encode("password"))
        userRepository.save(userOne)
    }

}