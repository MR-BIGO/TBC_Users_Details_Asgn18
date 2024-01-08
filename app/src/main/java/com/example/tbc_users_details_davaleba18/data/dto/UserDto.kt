package com.example.tbc_users_details_davaleba18.data.dto

import com.squareup.moshi.Json

data class UserDto(
    val id: Int,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "avatar")
    val img: String
)
