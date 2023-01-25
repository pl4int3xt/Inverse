package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformXXX(
    val name: String,
    val platform: Int,
    val slug: String
)