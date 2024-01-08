package com.example.tbc_users_details_davaleba18.data.repository

import com.example.tbc_users_details_davaleba18.data.common.HandleResponse
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.data.mapper.mapListToDomain
import com.example.tbc_users_details_davaleba18.data.mapper.toDomain
import com.example.tbc_users_details_davaleba18.data.service.IGetUsersService
import com.example.tbc_users_details_davaleba18.domain.model.User
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersRepositoryImpl @Inject constructor(
    private val getService: IGetUsersService,
    private val handler: HandleResponse
) : IGetUsersRepository {
    override suspend fun getUsers(): Flow<Resource<List<User>>> {
        return handler.safeApiCall {
            getService.getUsers()
        }.mapListToDomain { it.toDomain() }
    }
}