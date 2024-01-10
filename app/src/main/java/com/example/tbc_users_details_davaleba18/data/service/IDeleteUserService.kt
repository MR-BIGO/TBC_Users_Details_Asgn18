package com.example.tbc_users_details_davaleba18.data.service

import retrofit2.http.DELETE
import retrofit2.http.Path

interface IDeleteUserService {
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}