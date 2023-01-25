package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: Int,
    val store: StoreX
)