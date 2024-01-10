package com.example.tbc_users_details_davaleba18.domain.repository

import retrofit2.http.Body

interface IDeleteUserRepository {
    suspend fun deleteUser(@Body id: Int)
}