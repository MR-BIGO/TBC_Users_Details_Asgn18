package com.example.tbc_users_details_davaleba18.domain.use_case

import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.data.mapper.mapListToDomain
import com.example.tbc_users_details_davaleba18.domain.mapper.toPresentation
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUsersRepository
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: IGetUsersRepository) {

    suspend operator fun invoke(): Flow<Resource<List<UserItem>>> {
        return repository.getUsers().mapListToDomain { it.toPresentation() }
    }
}