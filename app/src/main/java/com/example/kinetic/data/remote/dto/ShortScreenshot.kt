package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShortScreenshot(
    val id: Int,
    val image: String
)