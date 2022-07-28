package com.example.sampleapp.data

import com.example.sampleapp.data.api.network_entity_model.NetworkMainResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers(
        "Content-Type: application/json",
        "X-RapidAPI-Key: 22376b57cdmsh1964956a19901eep10de21jsne982fc3fd17f",
        "X-RapidAPI-Host: covid-19-statistics.p.rapidapi.com"
    )
    @GET("regions")
    suspend fun getData(): NetworkMainResponse
}