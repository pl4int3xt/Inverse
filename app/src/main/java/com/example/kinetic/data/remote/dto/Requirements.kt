package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Requirements(
    val minimum: String? = "",
    val recommended: String? = "",
)