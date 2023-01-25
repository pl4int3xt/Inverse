package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StoreXXX(
    val domain: String,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)