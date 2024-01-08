package com.example.tbc_users_details_davaleba18.domain.mapper

import com.example.tbc_users_details_davaleba18.domain.model.User
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem

fun User.toPresentation(): UserItem {
    return UserItem(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        img = this.img,
        isSelected = false
    )
}