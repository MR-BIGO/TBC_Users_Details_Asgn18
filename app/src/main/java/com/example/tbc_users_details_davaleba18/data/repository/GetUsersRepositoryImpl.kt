package com.example.tbc_users_details_davaleba18.data.repository

import com.example.tbc_users_details_davaleba18.data.common.HandleResponse
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.data.service.IGetUsersService
import com.example.tbc_users_details_davaleba18.domain.model.User
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersRepositoryImpl @Inject constructor(
    private val getService: IGetUsersService,
    private val handler: HandleResponse
) : IGetUsersRepository {
    override suspend fun getUsers(): Flow<Resource<List<User>>> {
        return handler.safeApiCall {
            getService.getUsers()
        }.map {
            when(it){
                is Resource.Success -> Resource.Success(it.data)
                is Resource.Error -> Resource.Error(it.error)
                is Resource.Loading -> Resource.Loading(it.loading)
            }
        }
    }
}