package com.boycoder.kotlinjetpackinaction.entity

import java.util.*

data class User(
        val id: String? = null,
        val login: String? = null,
        val avatar_url: String? = null,
        val name: String? = null,
        val company: String? = null,
        val blog: String? = null,
        val lastRefresh: Date? = null
)