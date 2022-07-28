package com.example.sampleapp.data.api.network_entity_model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("iso")
    val iso: String,
    @SerializedName("name")

    val name: String,

    var expand : Boolean = false
)