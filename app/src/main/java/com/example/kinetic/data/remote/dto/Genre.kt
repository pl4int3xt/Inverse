package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val games_count: Int? = 0,
    val id: Int? = 0,
    val image_background: String? = "",
    val name: String? = "",
    val slug: String? = "",
)