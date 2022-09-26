package com.tungbt.app.config

import com.tungbt.app.service.MyUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var myUserDetailService: MyUserDetailService

    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeRequests { authorizeRequests -> authorizeRequests.anyRequest().authenticated() }
            .formLogin(withDefaults())
        return http.build()
    }

    @Bean
    fun authManager(userDetailService: MyUserDetailService, http: HttpSecurity): AuthenticationManager  {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java).userDetailsService(myUserDetailService)
            .passwordEncoder(this.encoder())
            .and()
            .authenticationProvider(this.authenticationProvider())
            .jdbcAuthentication()
            .dataSource(dataSource)
            .and().build()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider: DaoAuthenticationProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(myUserDetailService)
        authProvider.setPasswordEncoder(this.encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }

}