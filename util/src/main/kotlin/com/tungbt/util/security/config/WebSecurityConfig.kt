package com.tungbt.util.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class WebSecurityConfig {

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        /** Disable CORS, CSRF */
        http.cors().and().csrf().disable()
        /** Đẩy ra ngoại lệ */
        //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        /** Có khởi tạo session (STATELESS: Không khởi tạo session) */
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        /** Authenticate */
        .authorizeRequests().anyRequest().authenticated().and()
        /** Resource Server */
        .oauth2ResourceServer().jwt()
        /** authenticate provider */
        // http.authenticationProvider();
        /** Filter */
        //http.addFilterBefore()

        return http.build()
    }

}