package com.example.tbc_users_details_davaleba18.domain.repository

import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IGetUsersRepository {
    suspend fun getUsers(): Flow<Resource<List<User>>>
}