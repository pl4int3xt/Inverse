package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int? = 0,
    val id: Int? = 0,
    val percent: Double? = 0.00,
    val title: String? = "",
)