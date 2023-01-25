package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MetacriticPlatform(
    val metascore: Int,
    val platform: PlatformXXX,
    val url: String
)