package com.example.tbc_users_details_davaleba18.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.domain.use_case.GetUsersUseCase
import com.example.tbc_users_details_davaleba18.presentation.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {

    private val _itemFlow = MutableStateFlow<Resource<List<UserItem>>?>(null)
    val itemFlow: StateFlow<Resource<List<UserItem>>?> = _itemFlow.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke().collect{
                when(it){
                    is Resource.Success -> _itemFlow.value = Resource.Success(it.data)
                    is Resource.Error -> _itemFlow.value = Resource.Error(it.error)
                    is Resource.Loading -> _itemFlow.value = Resource.Loading(it.loading)
                }
            }
        }
    }
}