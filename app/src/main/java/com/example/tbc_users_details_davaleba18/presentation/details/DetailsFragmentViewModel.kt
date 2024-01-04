package com.example.tbc_users_details_davaleba18.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_users_details_davaleba18.data.common.Resource
import com.example.tbc_users_details_davaleba18.domain.model.SingleResponse
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private val getRepository: IGetUserRepository) : ViewModel() {

    private val _itemFlow = MutableStateFlow<Resource<SingleResponse>?>(null)
    val itemFLow: StateFlow<Resource<SingleResponse>?> = _itemFlow.asStateFlow()

    fun loadDetails(id: Int){
        viewModelScope.launch {
            getRepository.getUser(id).collect{
                when(it){
                    is Resource.Success -> _itemFlow.value = Resource.Success(it.data)
                    is Resource.Error -> _itemFlow.value = Resource.Error(it.error)
                    is Resource.Loading -> _itemFlow.value = Resource.Loading(it.loading)
                }
            }
        }
    }
}