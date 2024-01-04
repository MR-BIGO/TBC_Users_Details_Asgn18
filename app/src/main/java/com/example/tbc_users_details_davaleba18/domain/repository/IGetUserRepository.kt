package com.example.tbc_users_details_davaleba18.domain.repository

import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.domain.model.SingleResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface IGetUserRepository {
    suspend fun getUser(@Body id: Int): Flow<Resource<SingleResponse>>
}