package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformXXXXX(
    val platform: PlatformXXXXXX? = PlatformXXXXXX(),
    val released_at: String? = "",
    val requirements: Requirements? = Requirements(),
)