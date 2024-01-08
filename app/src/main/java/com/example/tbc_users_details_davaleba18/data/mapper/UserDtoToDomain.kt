package com.example.tbc_users_details_davaleba18.data.mapper

import com.example.tbc_users_details_davaleba18.data.dto.UserDto
import com.example.tbc_users_details_davaleba18.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        img = this.img
    )
}