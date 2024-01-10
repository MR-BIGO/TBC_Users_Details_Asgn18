package com.example.tbc_users_details_davaleba18.data.repository

import com.example.tbc_users_details_davaleba18.data.service.IDeleteUserService
import com.example.tbc_users_details_davaleba18.domain.repository.IDeleteUserRepository
import javax.inject.Inject

class DeleteUserRepositoryImpl @Inject constructor(private val deleteService: IDeleteUserService) :
    IDeleteUserRepository {
    override suspend fun deleteUser(id: Int) {
        deleteService.deleteUser(id)
    }
}