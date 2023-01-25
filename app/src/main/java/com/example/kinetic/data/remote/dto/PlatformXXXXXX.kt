package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformXXXXXX(
    val games_count: Int? = 0,
    val id: Int? = 0,
    val image: String? = "",
    val image_background: String? = "",
    val name: String? = "",
    val slug: String? = "",
    val year_end: Int? = 0,
    val year_start: Int? = 0,
)