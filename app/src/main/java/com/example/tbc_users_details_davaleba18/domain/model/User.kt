package com.example.tbc_users_details_davaleba18.domain.model

import com.squareup.moshi.Json

data class User(
    val id: Int,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "avatar")
    val img: String
)
