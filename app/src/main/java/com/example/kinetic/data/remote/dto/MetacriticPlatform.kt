package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MetacriticPlatform(
    val metascore: Int? = 0,
    val platform: PlatformXXX = PlatformXXX(),
    val url: String? = "",
)