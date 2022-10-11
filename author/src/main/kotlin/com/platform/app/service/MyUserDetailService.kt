package com.platform.app.service

import com.tungbt.app.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailService: UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository;

    override fun loadUserByUsername(username: String?): UserDetails? {
        val appUser = userRepository.findByUsername(username!!)
        return MyUserPrincipal(appUser)
    }


}