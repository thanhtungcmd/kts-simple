package com.tungbt.app.repository

import com.tungbt.app.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User

}