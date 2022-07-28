package com.example.sampleapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.api.network_entity_model.NetworkMainResponse
import com.example.sampleapp.data.repository.MainRepository
import com.example.sampleapp.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi

class MainViewModel
@ViewModelInject
constructor(
    private  val mainRepository: MainRepository
):ViewModel()

{

    private val _dataState: MutableLiveData<DataState<NetworkMainResponse>> = MutableLiveData()
    val datastate: LiveData<DataState<NetworkMainResponse>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetNetworkData ->{
                    mainRepository.getTrending()
                        .onEach { dataState ->
                            _dataState.value=dataState
                        }
                        .launchIn(viewModelScope)
                }

            }


        }
    }
}