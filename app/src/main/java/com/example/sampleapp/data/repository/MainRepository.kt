package com.example.sampleapp.data.repository

import com.example.sampleapp.data.ApiInterface
import com.example.sampleapp.data.api.network_entity_model.NetworkMainResponse
import com.example.sampleapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val apiInterface: ApiInterface
)

{

    suspend fun getTrending(): Flow<DataState<NetworkMainResponse>> = flow {
        emit(DataState.Loading)
        try {
            val networkResponse= apiInterface.getData()
            emit(DataState.Success(networkResponse))
        }
        catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}