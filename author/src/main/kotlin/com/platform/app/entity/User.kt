package com.tungbt.app.entity

import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null
)