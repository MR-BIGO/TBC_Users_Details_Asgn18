package com.example.tbc_users_details_davaleba18.presentation.main

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.domain.use_case.DeleteUsersUseCase
import com.example.tbc_users_details_davaleba18.domain.use_case.GetUsersUseCase
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase
) :
    ViewModel() {

    private val _itemFlow = MutableStateFlow<Resource<List<UserItem>>?>(null)
    val itemFlow: StateFlow<Resource<List<UserItem>>?> = _itemFlow.asStateFlow()

    private var usersList: List<UserItem> = emptyList()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        _itemFlow.value = Resource.Success(it.data)
                        usersList = it.data
                    }

                    is Resource.Error -> _itemFlow.value = Resource.Error(it.error)
                    is Resource.Loading -> _itemFlow.value = Resource.Loading(it.loading)
                }
            }
        }
    }

    fun deleteItems(items: List<Int>) {
        viewModelScope.launch {
            for (i in items){
                try {
                    //This doesn't work, because endpoint isn't supposed to work
                    deleteUsersUseCase.invoke(i)
                    //after deleting user/users, retrieving the new list again.
                    getUsers()
                }catch (e: Throwable){
                    d("delete error", e.message!!)
                }

            }
        }
    }
}