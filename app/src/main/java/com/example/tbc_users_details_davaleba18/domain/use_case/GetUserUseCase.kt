package com.example.tbc_users_details_davaleba18.domain.use_case

import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.data.mapper.mapToDomain
import com.example.tbc_users_details_davaleba18.domain.mapper.toPresentation
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUserRepository
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: IGetUserRepository) {

    suspend operator fun invoke(id: Int): Flow<Resource<UserItem>>{
        return repository.getUser(id).mapToDomain { it.toPresentation() }
    }
}