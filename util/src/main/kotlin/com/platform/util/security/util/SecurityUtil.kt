package com.platform.util.security.util

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class SecurityUtil {

    fun jwtGetInfo(): Map<String, Any> {
        val principal: JwtAuthenticationToken = SecurityContextHolder.getContext().authentication as JwtAuthenticationToken
        val authorities: Collection<String> = principal.authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList())

        val info: MutableMap<String, Any> = HashMap()
        info["name"] = principal.name
        info["authorities"] = authorities
        info["tokenAttributes"] = principal.tokenAttributes

        return info
    }

}