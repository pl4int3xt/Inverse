package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    val id: Int,
    val name: String,
    val slug: String
)