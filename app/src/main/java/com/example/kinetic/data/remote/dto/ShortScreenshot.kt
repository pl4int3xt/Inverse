package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShortScreenshot(
    val id: Int? = 0,
    val image: String? = "",
)