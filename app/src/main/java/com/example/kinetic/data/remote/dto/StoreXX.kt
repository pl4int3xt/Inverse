package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StoreXX(
    val id: Int? = 0,
    val store: StoreXXX? = StoreXXX(),
    val url: String? = "",
)