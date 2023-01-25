package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformXX(
    val games_count: Int,
    val id: Int,
    val image: String,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Int,
    val year_start: Int
)