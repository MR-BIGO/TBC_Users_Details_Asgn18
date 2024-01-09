package com.example.tbc_users_details_davaleba18.domain.use_case

import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUsersUseCase @Inject constructor() {

    suspend operator fun invoke(
        users: List<UserItem>,
        toDelete: List<Int>
    ): Flow<Resource<List<UserItem>>> {
        var updatedList = users

        updatedList = updatedList.filterNot { checker(it, toDelete) }

        return flow {
            emit(Resource.Success(updatedList))
        }
    }

    private fun checker(user: UserItem, toDelete: List<Int>): Boolean {
        for (i in toDelete) {
            if (user.id == i) return true
        }
        return false
    }
}