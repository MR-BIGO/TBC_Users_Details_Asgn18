package com.example.tbc_users_details_davaleba18.data.service

import com.example.tbc_users_details_davaleba18.data.dto.SingleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetUserService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<SingleResponseDto>
}