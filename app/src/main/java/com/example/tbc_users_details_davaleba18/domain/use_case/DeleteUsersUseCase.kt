package com.example.tbc_users_details_davaleba18.domain.use_case

import com.example.tbc_users_details_davaleba18.domain.repository.IDeleteUserRepository
import javax.inject.Inject

class DeleteUsersUseCase @Inject constructor(private val repository: IDeleteUserRepository) {

    suspend operator fun invoke(id: Int) {
        repository.deleteUser(id)
    }
}