package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)