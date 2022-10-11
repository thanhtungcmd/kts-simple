package com.platform.app.service

import com.tungbt.app.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

class MyUserPrincipal(user: User) : UserDetails {

    private val user: User = user

    override fun getUsername(): String? {
        return user.username
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return Collections.singleton(SimpleGrantedAuthority("User"))
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}