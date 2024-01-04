package com.example.tbc_users_details_davaleba18.data.repository

import com.example.tbc_users_details_davaleba18.data.common.HandleResponse
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.data.service.IGetUserService
import com.example.tbc_users_details_davaleba18.domain.model.SingleResponse
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(
    private val service: IGetUserService,
    private val handler: HandleResponse
) : IGetUserRepository {
    override suspend fun getUser(id: Int): Flow<Resource<SingleResponse>> {
        return handler.safeApiCall {
            service.getUser(id)
        }.map {
            when (it) {
                is Resource.Success -> Resource.Success(it.data)
                is Resource.Error -> Resource.Error(it.error)
                is Resource.Loading -> Resource.Loading(it.loading)
            }
        }
    }
}