package com.platform.util.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
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
        .authorizeRequests { authz ->
            authz.antMatchers("/test").permitAll()
            .anyRequest().authenticated()
        }
        /** Resource Server */
        .oauth2ResourceServer().jwt()

        return http.build()
    }

}