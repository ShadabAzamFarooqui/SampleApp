package com.example.sampleapp.data.api.network_entity_model

import com.google.gson.annotations.SerializedName

data class NetworkMainResponse(
    @SerializedName("data")

    val data: List<Data>
)