package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EsrbRatingX(
    val id: Int,
    val name: String,
    val slug: String
)